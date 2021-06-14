package com.example.mvvmsqllite

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.ViewModel

class LoginViewModel (private val listener: LoginResultCallBacks): ViewModel() {
    private val user: User

    init {
        this.user = User("", "")
    }

    val emailTextWatcher: TextWatcher
    get() = object: TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            user.setEmail(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }
    }

    val passwordTextWatcher: TextWatcher
    get() = object:TextWatcher{
        override fun afterTextChanged(s: Editable?) {
            user.setPassword(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }
    }

    fun onLoginClicked(v: View) {
        if (user.isDataValid)
            listener.onSuccess("Успешная авторизация.")
        else
            listener.onError("Произошла ошибка, введены неверные данные или пользователя не существует.")
    }
}