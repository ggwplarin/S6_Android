package com.example.financeapp.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.financeapp.BalanceChange
import com.example.financeapp.BalanceClass
import com.example.financeapp.R
import io.paperdb.Paper
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import kotlinx.android.synthetic.main.deposit_dialog.view.*
import kotlinx.android.synthetic.main.withdraw_dialog.view.*
import java.util.*


class BalanceFragment : Fragment() {

    lateinit var balanceText : TextView
    lateinit var balanceAdd : Button
    lateinit var balanceRemove : Button
    var totalBalanceDbl :Double? = 0.0


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Paper.init(context)
        totalBalance()

        balanceAdd.setOnClickListener {

            val depositDialogView = LayoutInflater.from(context).inflate(R.layout.deposit_dialog, null)
            val depositBuilder = AlertDialog.Builder(context)
                .setView(depositDialogView)
                .setTitle("Внести деньги")
            val depositAlert = depositBuilder.show()

            depositDialogView.closeDep.setOnClickListener {
                depositAlert.dismiss()

            }

            Observable.create(ObservableOnSubscribe<MutableList<BalanceChange>>{

                depositDialogView.applyDep.setOnClickListener {
                    var checkDeposit = true

                    if (depositDialogView.editTextDep.text.length == 0){
                        depositDialogView.editTextDep.setError("Поле не должно быть пустым")
                        checkDeposit = false
                    }

                    if (checkDeposit){

                        val typeOfDeposit = depositDialogView.spinnerDep.selectedItem.toString()

                        val itemOfDep = BalanceChange()

                        itemOfDep.reasonOf = typeOfDeposit
                        itemOfDep.priceOfChange = depositDialogView.editTextDep.text.toString().toDoubleOrNull()

                        BalanceClass.setChanges(itemOfDep)
                        d("money+", itemOfDep.toString())
                        Toast.makeText(context,"Сумма внесена на ваш кошелек",Toast.LENGTH_LONG).show()
                        totalBalance()
                        depositAlert.dismiss()
                    }
                }
            }).subscribe()


        }

        balanceRemove.setOnClickListener {
            val withdrawDialogView = LayoutInflater.from(context).inflate(R.layout.withdraw_dialog, null)
            val withdrawBuilder = AlertDialog.Builder(context)
                .setView(withdrawDialogView)
                .setTitle("Снять деньги")
            val withdrawAlert = withdrawBuilder.show()

            withdrawDialogView.closeWith.setOnClickListener {
                withdrawAlert.dismiss()
            }

            Observable.create(ObservableOnSubscribe<MutableList<BalanceChange>>{

                withdrawDialogView.applyWith.setOnClickListener {
                    var withdrawCheck = true

                    if (withdrawDialogView.editTextWith.text.length == 0){
                        withdrawCheck = false
                        withdrawDialogView.editTextWith.setError("Поле не должно быть пустым")
                    }
                    if (withdrawDialogView.editTextWith.text.toString().toDoubleOrNull()!! > totalBalanceDbl!!.toDouble()){

                        withdrawCheck = false
                        withdrawDialogView.editTextWith.setError("На вашем счете недостаточно средств для списания")

                    }
                    if (withdrawCheck){
                        val typeOfWithdraw = withdrawDialogView.spinnerWith.selectedItem.toString()
                        val priceOfWithdraw = -1 * withdrawDialogView.editTextWith.text.toString().toDoubleOrNull()!!

                        val itemOfWith = BalanceChange()

                        itemOfWith.reasonOf = typeOfWithdraw
                        itemOfWith.priceOfChange = priceOfWithdraw

                        BalanceClass.setChanges(itemOfWith)
                        d("money+", itemOfWith.toString())
                        Toast.makeText(context,"Сумма снята с вашего кошелек",Toast.LENGTH_LONG).show()
                        totalBalance()
                        withdrawAlert.dismiss()
                    }
                }
            }).subscribe()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val balanceFrag = inflater.inflate(R.layout.fragment_balance, container, false)
        balanceText = balanceFrag.findViewById(R.id.balanceText)
        balanceAdd = balanceFrag.findViewById(R.id.depositBtn)
        balanceRemove = balanceFrag.findViewById(R.id.withdrawBtn)

        return balanceFrag
    }

    fun totalBalance(){
        totalBalanceDbl = BalanceClass.getBalance()
            .fold(0.toDouble()) {acc, balanceChange ->  acc + balanceChange.priceOfChange!!.toDouble()}
        balanceText.text = "${totalBalanceDbl.toString()} руб."
    }

}