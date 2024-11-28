package com.example.forex

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class enterOtpActivity : AppCompatActivity() {

    fun getSavedCode(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
        return sharedPreferences.getString("verification_code", null)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_enter_otp)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val savedCode = getSavedCode(this)

        val resetPasswordButton: Button = findViewById(R.id.resetPasswordButton)
        resetPasswordButton.setOnClickListener {
            val code1 = findViewById<EditText>(R.id.code1).text.toString()
            val code2 = findViewById<EditText>(R.id.code2).text.toString()
            val code3 = findViewById<EditText>(R.id.code3).text.toString()
            val code4 = findViewById<EditText>(R.id.code4).text.toString()
            val code5 = findViewById<EditText>(R.id.code5).text.toString()

            val enteredCode = code1 + code2 + code3 + code4 + code5
            if (enteredCode == savedCode)
            {
                Toast.makeText(this, "Код подтверждения правильный", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(this, "Неверный код", Toast.LENGTH_SHORT).show()
            }
        }
    }
}