package com.example.forex

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.forex.models.User
import com.example.forex.models.UserListManage

class signInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toSignIn = findViewById<Button>(R.id.ToSignUpButton)
        toSignIn.setOnClickListener {
            val intent = Intent(this, signUpActivity::class.java)
            startActivity(intent)
        }

        val toForgetPassword = findViewById<Button>(R.id.forgotPasswordButton)
        toForgetPassword.setOnClickListener {
            val intent2 = Intent(this, forgetPasswordActivity::class.java)
            startActivity(intent2)
        }

        val toLastActivity = findViewById<Button>(R.id.loginButton)
        toLastActivity.setOnClickListener {
            val emailInput = findViewById<EditText>(R.id.emailEditText)
            val email = emailInput.text.toString()
            val passwordInput = findViewById<EditText>(R.id.passwordEditText)
            val password = passwordInput.text.toString()

            if(UserListManage.getUserByEmail(email)?.password!=password){
                Toast.makeText(this, "Неправильный логин или пароль", Toast.LENGTH_LONG).show()
            }
            else {
                val intent3 = Intent(this, lastActivity::class.java)
                startActivity(intent3)
            }
        }

    }
}