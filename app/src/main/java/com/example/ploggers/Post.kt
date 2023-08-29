package com.example.ploggers

data class Post(
    val content : String = "",
    val images : MutableList<String> = mutableListOf()
)