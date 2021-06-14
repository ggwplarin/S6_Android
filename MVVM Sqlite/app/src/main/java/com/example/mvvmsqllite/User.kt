package com.example.mvvmsqllite

import android.text.TextUtils
import android.util.Patterns
import androidx.databinding.BaseObservable
import java.util.regex.Pattern

class User(private var email: String, private var password: String): BaseObservable() {
    val isDataValid: Boolean
    get() = ((!TextUtils.isEmpty(email)) && Patterns.EMAIL_ADDRESS.matcher(email).matches() && (password.length > 6)
            && (getUsersInBD().map { x -> x.email }.contains(email) && getUsersInBD().map { x -> x.password }.contains(password)))

    private fun getUsersInBD(): ArrayList<User> {
        val data = DatabaseHelper(mainactivitycontext.getContext()!!)!!.showData()

        if (data!!.count == 0) {
            return arrayListOf()
        }

        val buffer = StringBuffer()
        var users: ArrayList<User> = arrayListOf()
        while (data.moveToNext()) {
            var user: User = User(data.getString(1), data.getString(2));
            //buffer.append("ID: ${data.getString(0)}\n")
            // display("Все пользователи", buffer.toString())
            users.add(user);
        }
        return users
    }

    fun getPassword(): String {
        return password
    }

    fun getEmail(): String {
        return email
    }

    fun setEmail(email: String) {
        this.email = email
    }

    fun setPassword(password: String) {
        this.password = password
    }
}