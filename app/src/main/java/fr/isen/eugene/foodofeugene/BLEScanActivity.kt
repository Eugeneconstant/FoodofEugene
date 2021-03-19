package fr.isen.eugene.foodofeugene

import android.bluetooth.BluetoothAdapter
import android.Manifest
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.*
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import fr.isen.eugene.foodofeugene.databinding.ActivityBLEScanBinding
import fr.isen.eugene.foodofeugene.databinding.ActivityCategoryBinding


class BLEScanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBLEScanBinding
    private var isScanning = false
    private var bluetoothAdapter: BluetoothAdapter? = null
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
                        ActivityCompat.requestPermissions(
                            this,
                            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                            REQUEST_PERMISSION_LOCATION
                        )
                    }
            else -> {
                //on peut faire le ble
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_ENABLE_BT && resultCode == RESULT_OK ){
            startBLEifPossible()
        }
    }
    private fun isDeviceHasBLESupport(): Boolean =
        packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)



    private fun togglePlayPauseAction(){
        isScanning = !isScanning
        if(isScanning){
            binding.bleScanPlayTitle.text = getString(R.string.ble_scan_pause_title)
            binding.loadingProgress.isVisible = true
            binding.divider.isVisible = false
        }else{
            binding.bleScanPlayTitle.text = getString(R.string.ble_scan_play_title)
            binding.loadingProgress.isVisible = false
            binding.divider.isVisible = true
        }
    }
    companion object{
        const private val REQUEST_ENABLE_BT = 33
        const private val REQUEST_PERMISSION_LOCATION = 22
    }
}