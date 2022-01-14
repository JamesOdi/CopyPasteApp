package james.learning.copypaste

import android.content.ClipData
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
        randomTextField.setText(getRandomText())

        val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        copyButton.setOnClickListener {
            val randomText = randomTextField.text.toString()
            val clip = ClipData.newPlainText("label",randomText)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this, "Copied to clipboard", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getRandomText() = UUID.randomUUID().toString()
}