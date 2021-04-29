package fr.isen.eugene.foodofeugene

import android.bluetooth.BluetoothDevice
import android.bluetooth.le.ScanRecord
import android.bluetooth.le.ScanResult
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.eugene.foodofeugene.Model.Device
import fr.isen.eugene.foodofeugene.databinding.ActivityBLEScanDetailBinding
import java.util.ArrayList


class BLEScanDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBLEScanDetailBinding
    private lateinit var listdevice: BluetoothDevice
    private var deviceList = ArrayList<Device>()
    private lateinit var devAdapter: DetailBleAdapter
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBLEScanDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listdevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE) ?: error("Manque ble device !!!")

        binding.namedevice.text = listdevice?.name
        binding.deviceAdress.text = listdevice?.address

        binding.devicecharacteristic.layoutManager = LinearLayoutManager(this)
        devAdapter = DetailBleAdapter(deviceList)
        binding.devicecharacteristic.adapter = devAdapter
        devAdapter.notifyDataSetChanged()

        val detail1 = Device("Coucou", "uuid", "proprietes", "10")
        deviceList.add(detail1)

    }

}