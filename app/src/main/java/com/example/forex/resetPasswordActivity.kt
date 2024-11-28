package com.example.forex

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.forex.models.User
import com.example.forex.models.UserListManage
import com.example.forex.models.UserSession

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
        val userEmail = UserSession.currentUserEmail



        val toEnterOtp = findViewById<ImageButton>(R.id.backArrow)
        toEnterOtp.setOnClickListener {
            val intent3 = Intent(this, enterOtpActivity::class.java)
            startActivity(intent3)
        }
        val toSignIn = findViewById<Button>(R.id.resetPasswordButton)
        toSignIn.setOnClickListener {
            val pass = findViewById<EditText>(R.id.passwordEditText)
            val password = pass.text.toString()
            val pass2 = findViewById<EditText>(R.id.passwordRepeatEditText)
            val password2 = pass2.text.toString()
            if (password == password2) {
                UserListManage.getUserByEmail(userEmail)?.password = password
                val intent2 = Intent(this, signInActivity::class.java)
                startActivity(intent2)
            }
            else{
                Toast.makeText(this, "Пароли не одинаковые", Toast.LENGTH_SHORT).show()
            }
        }


    }
}