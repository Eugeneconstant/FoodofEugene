package fr.isen.eugene.foodofeugene

import android.bluetooth.BluetoothDevice
import android.bluetooth.le.ScanRecord
import android.bluetooth.le.ScanResult
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.eugene.foodofeugene.databinding.CellCategoryBinding


class DeviceAdapter(private val listdevice: MutableList<ScanResult>): RecyclerView.Adapter<DeviceAdapter.DeviceViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DeviceViewHolder {
        val binding  = CellCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DeviceViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        holder.titledevice.text = listdevice[position].device.toString()
        holder.layout.setOnClickListener{

        }
    }
    fun addDevice(appareilData: ScanResult){
        if(!listdevice.contains(appareilData))
            listdevice.add(appareilData)
    }

    override fun getItemCount(): Int = listdevice.size

    class DeviceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titledevice: TextView = view.findViewById(R.id.devicetitle)
        val layout = view.findViewById<View>(R.id.cellDevice)
    }
}