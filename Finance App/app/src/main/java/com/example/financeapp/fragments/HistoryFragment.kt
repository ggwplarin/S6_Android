package com.example.financeapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import com.example.financeapp.R





class HistoryFragment : Fragment() {

    lateinit var statDepButton: Button
    lateinit var statWithButton: Button
    lateinit var billFrames : FrameLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val depositHistoryFragment = DepositHistoryFragment()
        val withdrawHistoryFragment = WithdrawHistoryFragment()
        makeCurrentFragment(depositHistoryFragment)

        statDepButton.setOnClickListener {
            makeCurrentFragment(depositHistoryFragment)
        }
        statWithButton.setOnClickListener {
            makeCurrentFragment(withdrawHistoryFragment)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val historyFragment = inflater.inflate(R.layout.fragment_history, container, false)
        statDepButton = historyFragment.findViewById(R.id.statDepBtn)
        statWithButton = historyFragment.findViewById(R.id.statWithBtn)
        billFrames = historyFragment.findViewById(R.id.billFrames)
        return historyFragment
    }

    fun makeCurrentFragment(fragment: Fragment) =
        requireFragmentManager().beginTransaction().apply {
            replace(R.id.billFrames , fragment)
            commit()
        }

}