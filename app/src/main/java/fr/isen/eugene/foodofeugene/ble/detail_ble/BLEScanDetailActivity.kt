package fr.isen.eugene.foodofeugene.ble.detail_ble

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCallback
import android.bluetooth.BluetoothGattCharacteristic
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.eugene.foodofeugene.Model.BLEService
import fr.isen.eugene.foodofeugene.ble.BLEConnexionState
import fr.isen.eugene.foodofeugene.R
import fr.isen.eugene.foodofeugene.databinding.ActivityBLEScanDetailBinding
import java.util.*


class BLEScanDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBLEScanDetailBinding
    private lateinit var listdevice: BluetoothDevice
    var bluetoothGatt: BluetoothGatt? = null
    private lateinit var listservice: MutableList<BLEService>
    private var context : Context? = null
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBLEScanDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listdevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE) ?: error("Manque ble device !!!")
        binding.namedevice.text = listdevice?.name ?: "Appareil inconnu"
        binding.devicestatus.text = getString(R.string.ble_device_status, getString(R.string.ble_device_status_connecting))

        binding.bleservice.layoutManager = LinearLayoutManager(this)
        connectToDevice(listdevice)


    }

    private fun connectToDevice(device: BluetoothDevice?){
        bluetoothGatt = device?.connectGatt(this, false, object: BluetoothGattCallback(){
            override fun onConnectionStateChange(gatt: BluetoothGatt, status: Int, newState: Int){
                super.onConnectionStateChange(gatt, status, newState)
                connectionStateChange(newState, gatt)
            }

            override fun onServicesDiscovered(gatt: BluetoothGatt, status: Int) {
                super.onServicesDiscovered(gatt, status)
                gatt.services?.let{
                   listservice = it.map{
                       BLEService(it.uuid.toString(), it.characteristics)
                   }.toMutableList()
                    binding.bleservice.adapter = DetailDeviceAdapter(listservice, bluetoothGatt, context)
                }
            }

            override fun onCharacteristicRead(gatt: BluetoothGatt?, characteristic: BluetoothGattCharacteristic?, status: Int) { //Lecture
                super.onCharacteristicRead(gatt, characteristic, status)
                runOnUiThread {
                    binding.bleservice.adapter?.notifyDataSetChanged()
                }
            }

            override fun onCharacteristicWrite(gatt: BluetoothGatt?, characteristic: BluetoothGattCharacteristic?, status: Int) { //Ecriture
                super.onCharacteristicWrite(gatt, characteristic, status)
                runOnUiThread {
                    binding.bleservice.adapter?.notifyDataSetChanged()
                }
            }

            override fun onCharacteristicChanged(gatt: BluetoothGatt?, characteristic: BluetoothGattCharacteristic?) { //Notification
                super.onCharacteristicChanged(gatt, characteristic)
                runOnUiThread {
                    binding.bleservice.adapter?.notifyDataSetChanged()
                }
            }
        })
    }

    private fun connectionStateChange(newState: Int, gatt: BluetoothGatt?){

            BLEConnexionState.getBLEConnexionStateFromState(newState)?.let {
                runOnUiThread {
                    binding.devicestatus.text = getString(R.string.ble_device_status, getString(it.text))
                }
                if (it.state == BLEConnexionState.STATE_CONNECTED.state) {
                    gatt?.discoverServices()
                }

            }

    }





}