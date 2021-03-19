package fr.isen.eugene.foodofeugene

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.*
import android.Manifest
import android.bluetooth.BluetoothDevice
import android.bluetooth.le.BluetoothLeScanner
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.os.Handler
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.eugene.foodofeugene.databinding.ActivityBLEScanBinding

class BLEScanActivity() : AppCompatActivity() {
    private lateinit var binding: ActivityBLEScanBinding
    private var isScanning = false
    private var bluetoothAdapter: BluetoothAdapter? = null
    private var bluetoothLeScanner: BluetoothLeScanner? = bluetoothAdapter?.bluetoothLeScanner
    private var scanning = false
    private var listDevice = mutableListOf<BluetoothDevice>()
    private val handler = Handler()
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBLEScanBinding.inflate(layoutInflater)
        setContentView(binding.root)


        bluetoothAdapter = getSystemService(BluetoothManager::class.java)?.adapter

        startBLEifPossible()


        binding.bleScanPlayPauseAction.setOnClickListener {
            togglePlayPauseAction()
        }

        binding.bleScanPlayTitle.setOnClickListener{
            togglePlayPauseAction()
        }


    }

    private fun startBLEifPossible() {
        when {
            !isDeviceHasBLESupport() || bluetoothAdapter == null -> {
                Toast.makeText(this, "Cet appareil n'est pas compatible avec le BLE", LENGTH_SHORT).show()
            }
            !(bluetoothAdapter?.isEnabled ?: false) -> {
                //activation du ble
                val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
            }
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED ->{
                        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        REQUEST_PERMISSION_LOCATION)
                    }
            else -> {
                //on peut faire le ble
                Log.d("ScanDevices", "onRequestPermissionsResult(not PERMISSION")
                bluetoothLeScanner = bluetoothAdapter?.bluetoothLeScanner
                initRecyclerDevice()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_ENABLE_BT && resultCode == RESULT_OK ){
            startBLEifPossible()
        }
    }

    private fun initRecyclerDevice(){
        leDeviceListAdapter = DeviceAdapter(mutableListOf())
        binding.bleRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.bleRecyclerView.adapter = leDeviceListAdapter
    }
    private fun isDeviceHasBLESupport(): Boolean =
        packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)
    // Stops scanning after 10 seconds.
    private val SCAN_PERIOD: Long = 10000
    private fun scanLeDevice() {
        bluetoothLeScanner?.let { scanner ->
            if (!scanning) { // Stops scanning after a pre-defined scan period.
                handler.postDelayed({
                    scanning = false
                    scanner.stopScan(leScanCallback)
                }, SCAN_PERIOD)
                scanning = true
                scanner.startScan(leScanCallback)
            } else {
                scanning = false
                scanner.stopScan(leScanCallback)
            }
        }
    }
    private var leDeviceListAdapter = DeviceAdapter(listDevice)
    // Device scan callback.
    private val leScanCallback: ScanCallback = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult) {
            super.onScanResult(callbackType, result)
            leDeviceListAdapter.addDevice(result.device)
            leDeviceListAdapter.notifyDataSetChanged()
        }
    }

    private fun togglePlayPauseAction(){
        isScanning = !isScanning
        if(isScanning){
            binding.bleScanPlayTitle.text = getString(R.string.ble_scan_pause_title)
            binding.loadingProgress.isVisible = true
            binding.divider.isVisible = false
            scanLeDevice()
        }else{
            binding.bleScanPlayTitle.text = getString(R.string.ble_scan_play_title)
            binding.loadingProgress.isVisible = false
            binding.divider.isVisible = true
        }
    }

    companion object{
        const private val REQUEST_ENABLE_BT = 33
        const private val REQUEST_PERMISSION_LOCATION = 33
    }

}