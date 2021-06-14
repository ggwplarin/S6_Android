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
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry


class WithdrawHistoryFragment : Fragment() {

    lateinit var piechartWithdraw: PieChart
    lateinit var allSum2 : TextView
    var all2 : Double? = 0.0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setPieChar()
        allSum2.text = "${all2.toString()}"

    }

    fun setPieChar() {

        val xvalues = ArrayList<String>()
        xvalues.add("Развлечение")
        xvalues.add("Продукты")
        xvalues.add("Одежда")
        xvalues.add("Здоровье")
        xvalues.add("Другое")

        val listOf = BalanceClass.getBalance()
        var pay1 = 0.0
        var pay2 = 0.0
        var pay3 = 0.0
        var pay4 = 0.0
        var pay5 = 0.0
        val piechartEntry = ArrayList<PieEntry>()
        for (i in 0 .. listOf.size - 1){

            if ((listOf[i].priceOfChange!! < 0) && (listOf[i].reasonOf == "Развлечение")){
                pay1 += listOf[i].priceOfChange!!.toDouble()
            }
            if ((listOf[i].priceOfChange!! < 0)&&(listOf[i].reasonOf == "Продукты")){
                pay2 += listOf[i].priceOfChange!!.toDouble()
            }
            if ((listOf[i].priceOfChange!! < 0)&&(listOf[i].reasonOf == "Одежда")){
                pay3 += listOf[i].priceOfChange!!.toDouble()
            }
            if ((listOf[i].priceOfChange!! < 0)&&(listOf[i].reasonOf == "Здоровье")){
                pay4 += listOf[i].priceOfChange!!.toDouble()
            }
            if ((listOf[i].priceOfChange!! < 0)&&(listOf[i].reasonOf == "Другое")){
                pay5 += listOf[i].priceOfChange!!.toDouble()
            }

        }

        all2 = -1 * (pay1 + pay2 + pay3 + pay4 + pay5)
        val float1 = (-1 * (pay1/ all2!! * 100)).toFloat()
        val float2 = (-1 * (pay2/ all2!! * 100)).toFloat()
        val float3 = (-1 * (pay3/ all2!! * 100)).toFloat()
        val float4 = (-1 * (pay4/ all2!! * 100)).toFloat()
        val float5 = (-1 * (pay5/ all2!! * 100)).toFloat()

        piechartEntry.add(PieEntry(float1,xvalues[0]))
        piechartEntry.add(PieEntry(float2,xvalues[1]))
        piechartEntry.add(PieEntry(float3,xvalues[2]))
        piechartEntry.add(PieEntry(float4,xvalues[3]))
        piechartEntry.add(PieEntry(float5,xvalues[4]))


        val colors = ArrayList<Int>()
        colors.add(Color.RED)
        colors.add(Color.BLUE)
        colors.add(Color.LTGRAY)
        colors.add(Color.YELLOW)
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
        piechartWithdraw.data = data

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val withdrawHistoryFragment = inflater.inflate(R.layout.fragment_withdraw_history, container, false)
        piechartWithdraw = withdrawHistoryFragment.findViewById(R.id.piechartWithdraw)
        allSum2 = withdrawHistoryFragment.findViewById(R.id.allSum2)
        return  withdrawHistoryFragment
    }

}