package james.learning.copypaste

import android.content.ClipData
import android.content.ClipDescription.MIMETYPE_TEXT_PLAIN
import android.content.ClipboardManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val randomTextField = findViewById<EditText>(R.id.randomText)
        val copyButton = findViewById<Button>(R.id.copyButton)
        val pasteButton = findViewById<Button>(R.id.pasteButton)
        val inputEditText = findViewById<EditText>(R.id.plainEditText)
        
        randomTextField.setText(getRandomText())

        val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        copyButton.setOnClickListener {
            val randomText = randomTextField.text.toString()
            val clip = ClipData.newPlainText("label",randomText)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this, "Copied to clipboard!", Toast.LENGTH_SHORT).show()
        }
        
        pasteButton.setOnClickListener {
            if (!clipboard.hasPrimaryClip()) {
                Toast.makeText(this, "Nothing to paste!", Toast.LENGTH_SHORT).show()
            } else if (!(clipboard.primaryClipDescription?.hasMimeType(MIMETYPE_TEXT_PLAIN))!!) {
                Toast.makeText(this, "Object cannot be pasted here!", Toast.LENGTH_SHORT).show()
            } else {
                val clipItem = clipboard.primaryClip?.getItemAt(0)!!
                inputEditText.setText(clipItem.text.toString())
            }
        }
    }

    private fun getRandomText() = UUID.randomUUID().toString()
}