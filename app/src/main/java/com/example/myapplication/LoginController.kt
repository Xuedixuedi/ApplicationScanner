package com.example.myapplication

import android.text.Editable

class LoginController {
    internal fun numberRequired(phoneLength: Int, verifiedLength: Int): Boolean {
        return (phoneLength == 11 && verifiedLength == 6)
    }
}