package fr.isen.eugene.foodofeugene.ble.detail_ble

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.isen.eugene.foodofeugene.Model.Device
import fr.isen.eugene.foodofeugene.databinding.CellDeviceDetailBinding

class DetailBleAdapter(private var deviceList: List<Device>): RecyclerView.Adapter<DetailBleAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: CellDeviceDetailBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CellDeviceDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(deviceList[position]){
                binding.characteristicdevice.text = this.message
                binding.expandedrecyclerview.visibility = if (this.expand) View.VISIBLE else View.GONE
            }
        }
    }

    override fun getItemCount(): Int {
        return deviceList.size
    }
}