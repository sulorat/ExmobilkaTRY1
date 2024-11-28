package com.example.forex

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class resetPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reset_password)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val toEnterOtp = findViewById<ImageButton>(R.id.backArrow)
        toEnterOtp.setOnClickListener {
            val intent3 = Intent(this, enterOtpActivity::class.java)
            startActivity(intent3)
        }
        val toSignIn = findViewById<Button>(R.id.resetPasswordButton)
        toSignIn.setOnClickListener {
            val intent2 = Intent(this, signInActivity::class.java)
            startActivity(intent2)
        }
    }
}