package com.example.financeapp.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.financeapp.BalanceClass
import com.example.financeapp.R
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.*


class DepositHistoryFragment : Fragment() {

    lateinit var piechartDeposit: PieChart
    lateinit var allSum : TextView
    var all : Double? = 0.0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setPieChart()
        allSum.text = "Внесено всего: ${all.toString()}"

    }

    fun setPieChart() {

        val xvalues = ArrayList<String>()
        xvalues.add("Заработная плата")
        xvalues.add("Переводы")
        xvalues.add("Другое")

        val listOf = BalanceClass.getBalance()
        var pay1 = 0.0
        var pay2 = 0.0
        var pay3 = 0.0
        val piechartEntry = ArrayList<PieEntry>()
        for (i in 0 .. listOf.size - 1){

            if ((listOf[i].priceOfChange!! > 0) && (listOf[i].reasonOf == "Заработная плата")){
                pay1 += listOf[i].priceOfChange!!.toDouble()
            }
            if ((listOf[i].priceOfChange!! > 0)&&(listOf[i].reasonOf == "Переводы")){
                pay2 += listOf[i].priceOfChange!!.toDouble()
            }
            if ((listOf[i].priceOfChange!! > 0)&&(listOf[i].reasonOf == "Другое")){
                pay3 += listOf[i].priceOfChange!!.toDouble()
            }

        }

        all = pay1 + pay2 + pay3
        val float1 = (pay1/ all!! * 100).toFloat()
        val float2 = (pay2/ all!! * 100).toFloat()
        val float3 = (pay3/ all!! * 100).toFloat()

        piechartEntry.add(PieEntry(float1,xvalues[0]))
        piechartEntry.add(PieEntry(float2,xvalues[1]))
        piechartEntry.add(PieEntry(float3,xvalues[2]))


        val colors = ArrayList<Int>()
        colors.add(Color.CYAN)
        colors.add(Color.MAGENTA)
        colors.add(Color.GREEN)

        val colorsText = ArrayList<Int>()
        colorsText.add(Color.BLACK)
        colorsText.add(Color.BLACK)
        colorsText.add(Color.BLACK)

        val piedata = PieDataSet(piechartEntry, "")
        piedata.colors = colors
        piedata.sliceSpace = 3f
        piedata.setValueTextColor(Color.BLACK)
        val data = PieData(piedata)
        piechartDeposit.data = data
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val depositHistoryFragment = inflater.inflate(R.layout.fragment_deposit_history, container, false)
        allSum = depositHistoryFragment.findViewById(R.id.allSum)
        piechartDeposit = depositHistoryFragment.findViewById(R.id.piechartDeposit)
        return depositHistoryFragment
    }

}