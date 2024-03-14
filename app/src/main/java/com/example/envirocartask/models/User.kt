package com.example.envirocartask.models

data class User(
    val acceptedPrivacyStatementVersion: String,
    val acceptedTermsOfUseVersion: String,
    val created: String,
    val mail: String,
    val modified: String,
    val name: String
)