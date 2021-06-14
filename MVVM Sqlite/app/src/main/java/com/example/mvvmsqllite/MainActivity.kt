package com.example.mvvmsqllite

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmsqllite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), LoginResultCallBacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val activityManiBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        activityManiBinding.viewModel = ViewModelProviders.of(this, LoginViewModelFactory(this))
            .get(LoginViewModel::class.java)

        mainactivitycontext.setContext(this@MainActivity)
    }

    override fun onSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun getUsersInBD(): ArrayList<User> {
        val data = DatabaseHelper(mainactivitycontext.getContext()!!)!!.showData()

        if (data!!.count == 0) {
            display("Error", "Нет данных")
            return arrayListOf()
        }

        val buffer = StringBuffer()
        var users: ArrayList<User> = arrayListOf()
        while (data.moveToNext()) {
            var user: User = User(data.getString(1), data.getString(2));
            users.add(user);
        }
        return users
    }

    private fun display(title: String?, message: String?) {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.show()
    }
}