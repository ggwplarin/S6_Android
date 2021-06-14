package com.example.financeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.financeapp.fragments.BalanceFragment
import com.example.financeapp.fragments.HistoryFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val balanceFragment = BalanceFragment()
        val historyFragment = HistoryFragment()

        makeCurrentFragment(balanceFragment)

        bottom_nav.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.balance -> makeCurrentFragment(balanceFragment)
                R.id.graph -> makeCurrentFragment(historyFragment)
            }
            true
        }

    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_click, fragment)
            commit()
        }
}