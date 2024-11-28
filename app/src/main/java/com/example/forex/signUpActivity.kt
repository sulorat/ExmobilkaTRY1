package com.example.forex

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
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
import com.example.forex.models.UserListManage.isEmailTaken
import java.util.jar.Attributes.Name

class signUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val toLastActivity = findViewById<Button>(R.id.createAccountButton)
        toLastActivity.setOnClickListener {
            val emailInput = findViewById<EditText>(R.id.emailEditText)
            val email = emailInput.text.toString()
            val nameInput = findViewById<EditText>(R.id.nameEditText)
            val name = nameInput.text.toString()
            val passwordInput = findViewById<EditText>(R.id.passwordEditText)
            val password = passwordInput.text.toString()
            if(isEmailTaken(email)){
                Toast.makeText(this, "Почта или номер используется", Toast.LENGTH_SHORT).show()
            }
            else
            {
                UserListManage.addUser(User(name=name, emailOrPhone = email,password=password))
                val intent = Intent(this, lastActivity::class.java)
                startActivity(intent)
            }
        }
        val toSignIn = findViewById<Button>(R.id.signInButton)
        toSignIn.setOnClickListener {
            val intent = Intent(this, signInActivity::class.java)
            startActivity(intent)
        }
    }
}