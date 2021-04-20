package fr.isen.eugene.foodofeugene

import android.bluetooth.le.ScanResult
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.eugene.foodofeugene.databinding.CellCategoryBinding
import fr.isen.eugene.foodofeugene.databinding.CellDeviceBinding


class DeviceAdapter(private val listdevice: MutableList<ScanResult>): RecyclerView.Adapter<DeviceAdapter.DeviceViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DeviceViewHolder {
        val binding = CellDeviceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DeviceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        holder.deviceAddress.text = listdevice[position].device.toString()
        holder.titledevice.text = listdevice[position].scanRecord?.deviceName.toString()
        holder.layout.setOnClickListener{

        }
    }
    fun addDevice(appareilData: ScanResult){
        if(!listdevice.contains(appareilData))
            listdevice.add(appareilData)
    }

    override fun getItemCount(): Int = listdevice.size

    class DeviceViewHolder(binding: CellDeviceBinding) : RecyclerView.ViewHolder(binding.root) {
        val titledevice: TextView = itemView.findViewById(R.id.devicetitle)
        val deviceAddress : TextView = itemView.findViewById(R.id.adresseDevice)
        val layout = itemView.findViewById<View>(R.id.cellDevice)
    }
}