package com.example.forex

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class forgetPasswordActivity : AppCompatActivity() {

    private fun generateRandomCode(): String {
        val code = Random.nextInt(10000, 99999)
        return code.toString()
    }

    private fun sendSms(phoneNumber: String, code: String) {
            val smsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(phoneNumber, null, "Ваш код подтверждения: $code", null, null)
            Toast.makeText(this, "SMS отправлено успешно", Toast.LENGTH_SHORT).show()
        }

    fun saveCode(context: Context, code: String) {
        val sharedPreferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("verification_code", code)
        editor.apply()
    }


    private fun isValidPhoneNumber(input: String): Boolean {
        return input.first()=='8'&&input.length==11;
    }

    private fun sendEmail(email: String, code: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "message/95843"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
        intent.putExtra(Intent.EXTRA_SUBJECT, "Код подтверждения")
        intent.putExtra(Intent.EXTRA_TEXT, "Ваш код подтверждения: $code")

        try {
            startActivity(Intent.createChooser(intent, "Отправка email"))
            Toast.makeText(this, "Email отправлен успешно", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Ошибка отправки email", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isValidEmail(input: String): Boolean {
        return input.contains("@")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_forget_password)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toSignIn = findViewById<ImageButton>(R.id.backArrow)
        toSignIn.setOnClickListener {
            val intent3 = Intent(this, signInActivity::class.java)
            startActivity(intent3)
        }

        val emailOrMobileInput = findViewById<EditText>(R.id.emailOrMobileInput)
        val sendButton = findViewById<Button>(R.id.continueButton)

        sendButton.setOnClickListener {
            val input = emailOrMobileInput.text.toString()
            val code = generateRandomCode()
            saveCode(this, code)

            if (input.isNotEmpty()) {
                if (isValidEmail(input)) {
                    sendEmail(input, code)
                    val intent2 = Intent(this, enterOtpActivity::class.java)
                    startActivity(intent2)
                } else if (isValidPhoneNumber(input)) {
                    sendSms(input, code)
                    val intent2 = Intent(this, enterOtpActivity::class.java)
                    startActivity(intent2)
                } else {
                    Toast.makeText(
                        this,
                        "Введите действительный номер телефона или email",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            else
            {
                Toast.makeText(this, "Поле не может быть пустым", Toast.LENGTH_SHORT).show()
            }



            }

        }
    }
