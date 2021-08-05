package com.example.myapplication

import kotlin.contracts.contract

data class Login(
    var phone: String,
    var verifiedCode: String,
    var button: Boolean = false
)