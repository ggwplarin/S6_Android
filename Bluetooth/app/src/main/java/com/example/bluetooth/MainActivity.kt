@file:Suppress("DEPRECATION")

package com.example.bluetooth

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var mBlueAdapter: BluetoothAdapter? = null
    var statusBluetoothTv = findViewById<TextView>(R.id.statusBluetoothTv)
    var pairedTv = findViewById<TextView>(R.id.pairedTv)
    var onBtn = findViewById<Button>(R.id.onBtn)
    var offBtn = findViewById<Button>(R.id.offBtn)
    var discoverableBtn = findViewById<Button>(R.id.discoverableBtn)
    var pairedBtn = findViewById<Button>(R.id.pairedBtn)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBlueAdapter = BluetoothAdapter.getDefaultAdapter()

        if (mBlueAdapter == null) {
            statusBluetoothTv.text = "Bluetooth недоступен"
        } else {
            statusBluetoothTv.text = "Bluetooth доступен"
        }

        onBtn.setOnClickListener {
            if (!mBlueAdapter!!.isEnabled()){
                showToast("Включение Bluetooth...")
                val intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(intent, REQUEST_ENABLE_BT)
            } else {
                showToast("Bluetooth включен")
            }
        }

        discoverableBtn.setOnClickListener {
            if (!mBlueAdapter!!.isDiscovering()){
                showToast("Сделайте ваше устройство видимым для других")
                val intent = Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE)
                startActivityForResult(intent, REQUEST_DISCOVER_BT)
            }
        }

        offBtn.setOnClickListener {
            if (mBlueAdapter!!.isEnabled()){
                mBlueAdapter!!.disable()
                showToast("Bluetooth выключается")
            } else {
                showToast("Bluetooth выключен")
            }
        }

        pairedBtn.setOnClickListener {
            if (mBlueAdapter!!.isEnabled()){
                pairedTv.setText("Сопряженные устройства")
                val devices = mBlueAdapter!!.getBondedDevices()
                for (device in devices) {
                    pairedTv.append("""
                    ${device.name}, $device
                    """.trimIndent())
                }
            } else {
                showToast("Turn on bluetooth to get paired devices")
            }
        }
    }

    private  fun showToast(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val REQUEST_ENABLE_BT = 0
        private const val REQUEST_DISCOVER_BT = 1
    }
}