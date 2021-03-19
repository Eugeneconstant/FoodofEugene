package fr.isen.eugene.foodofeugene

import android.bluetooth.BluetoothDevice
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.eugene.foodofeugene.databinding.CellCategoryBinding


class DeviceAdapter(private val listdevice: MutableList<BluetoothDevice>): RecyclerView.Adapter<DeviceAdapter.DeviceViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DeviceViewHolder {
        val binding  = CellCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DeviceViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        holder.titledevice.text = listdevice[position].toString()
        holder.layout.setOnClickListener{
            //clickListener.onItemClicked(listdevice[position])
        }
    }
    fun addDevice(device: BluetoothDevice){
        if(!listdevice.contains(device))
            listdevice.add(device)
    }
    override fun getItemCount(): Int = listdevice.size

    class DeviceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titledevice: TextView = view.findViewById(R.id.devicetitle)
        val layout = view.findViewById<View>(R.id.cellDevice)
    }

    interface onItemClickListener {
        fun onItemClicked(device: BluetoothDevice)
    }


}