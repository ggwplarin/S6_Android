package com.example.mvvmsqllite

interface LoginResultCallBacks {
    fun onSuccess(message: String)
    fun onError(message: String)
}