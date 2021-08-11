package com.example.myapplication.Login

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.myapplication.List.ListActivity
import com.example.myapplication.R

class MainActivity : AppCompatActivity() {

    private var phoneLength: Int = 0
    private var verifiedLength: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        val textButton = findViewById<TextView>(R.id.textView5)

        val editPhoneText = findViewById<EditText>(R.id.editTextPhone)
        val editVerifiedCodeText = findViewById<EditText>(R.id.editTextNumberSigned)

        button.isEnabled = false

        loadData()

        editPhoneText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s != null) {
                    phoneLength = s.length
                    Log.d("1", phoneLength.toString())
                }
                button.isEnabled = LoginController().numberRequired(phoneLength, verifiedLength);
            }
        })

        editVerifiedCodeText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s != null) {
                    verifiedLength = s.length
                    Log.d("2", phoneLength.toString())
                }
                button.isEnabled = LoginController().numberRequired(phoneLength, verifiedLength);
            }
        })

        button.setOnClickListener {
            Toast.makeText(this, "跳转页面", Toast.LENGTH_SHORT).show();

            saveData()

            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }

        textButton.setOnClickListener {
            Toast.makeText(this, "验证码发送成功", Toast.LENGTH_SHORT).show();
        }

    }

    @SuppressLint("SetTextI18n")
    private fun saveData() {
        val phoneText = findViewById<EditText>(R.id.editTextPhone).text.toString()
        val textView = findViewById<TextView>(R.id.textView6)
        textView.setText("Your Phone Number:$phoneText")

        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply {
            putString("STRING_KEY", phoneText)
        }.apply()

        Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("SetTextI18n")
    private fun loadData() {
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val sevedString = sharedPreferences.getString("STRING_KEY", null)

        val textView = findViewById<TextView>(R.id.textView6)
        textView.setText("Your Phone Number:$sevedString")
    }

}


