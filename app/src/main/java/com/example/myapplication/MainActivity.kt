package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    private var phoneLength: Int = 0
    private var verifiedLength: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        val editPhoneText = findViewById<EditText>(R.id.editTextPhone)
        val editVerifiedCodeText = findViewById<EditText>(R.id.editTextNumberSigned)

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
                button.isEnabled = LoginController().numberRequired(phoneLength,verifiedLength);
            }
        })

        editVerifiedCodeText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s != null) {
                    verifiedLength = s.length
                    Log.d("2", phoneLength.toString())
                }
                button.isEnabled = LoginController().numberRequired(phoneLength,verifiedLength);
            }
        })

        button.setOnClickListener {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        }

    }
}


