package com.example.financeapp

import io.paperdb.Paper

class BalanceClass {

    companion object{

        fun setChanges(balanceChange: BalanceChange){

            val balance = BalanceClass.getBalance()
            balance.add(balanceChange)
            BalanceClass.saveBalance(balance)
        }

        fun saveBalance(balance : MutableList<BalanceChange>){
            Paper.book("balance").write("balance", balance)
        }

        fun getBalance() : MutableList<BalanceChange> {
            return Paper.book("balance").read("balance", mutableListOf())
        }

    }

}