package fr.isen.eugene.foodofeugene.ble.detail_ble


import android.bluetooth.BluetoothGattCharacteristic
import android.provider.Settings.Global.getString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.BatchingListUpdateCallback
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder
import fr.isen.eugene.foodofeugene.Model.BLEService
import fr.isen.eugene.foodofeugene.R

class DetailDeviceAdapter(private val serviceList: MutableList<BLEService>):
        ExpandableRecyclerViewAdapter<DetailDeviceAdapter.ServiceViewHolder, DetailDeviceAdapter.CharacteristicsViewHolder>
        (serviceList) {

    class CharacteristicsViewHolder(itemView: View): ChildViewHolder(itemView) {

        val characteristicSpecific: TextView = itemView.findViewById(R.id.characteristiquespecifique)
        val characteristicUUID: TextView = itemView.findViewById(R.id.uuid)
        val characteristicProperties: TextView = itemView.findViewById(R.id.proprietes)


        val characteristicRead = itemView.findViewById<Button>(R.id.lecture)
        val characteristicWrite = itemView.findViewById<Button>(R.id.ecriture)
        val characteristicNotify = itemView.findViewById<Button>(R.id.notifier)
    }

    class ServiceViewHolder(itemView: View): GroupViewHolder(itemView) {

        val serviceName: TextView = itemView.findViewById(R.id.deviceservicetitle)
        val serviceAddress: TextView = itemView.findViewById(R.id.deviceserviceaddress)
        private val servieArrow = itemView.findViewById<ImageView>(R.id.arrowimg)
        override fun expand() {
            super.expand()
            servieArrow.animate().rotation(-180f).setDuration(400L).start()
        }

        override fun collapse() {
            super.collapse()
            servieArrow.animate().rotation(0f).setDuration(400L).start()
        }
    }

    override fun onCreateGroupViewHolder(
            parent: ViewGroup,
            viewType: Int):
            ServiceViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_service, parent, false)
        return ServiceViewHolder(view)
    }

    override fun onCreateChildViewHolder(
            parent: ViewGroup,
            viewType: Int):
            CharacteristicsViewHolder{
        val view2 = LayoutInflater.from(parent.context).inflate(R.layout.cell_characteristic, parent, false)
        return CharacteristicsViewHolder(view2)
    }

    override fun onBindChildViewHolder(
            holder: CharacteristicsViewHolder,
            flatPosition: Int,
            group: ExpandableGroup<*>,
            childIndex: Int) {
            holder.characteristicSpecific.text = "Characteristic Specifique"
            holder.characteristicUUID.text = "UUID : " + (group.items[childIndex] as BluetoothGattCharacteristic).uuid.toString()
            holder.characteristicProperties.text = "Properties : " + (group.items[childIndex] as BluetoothGattCharacteristic).properties.toString()

    }

    override fun onBindGroupViewHolder(
            holder: ServiceViewHolder,
            flatPosition: Int,
            group: ExpandableGroup<*>) {
            holder.serviceName.text = group.title


    }

}