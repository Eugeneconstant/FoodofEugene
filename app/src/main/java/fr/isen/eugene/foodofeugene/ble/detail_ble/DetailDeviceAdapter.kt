package fr.isen.eugene.foodofeugene.ble.detail_ble


import android.app.AlertDialog
import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCharacteristic
import android.bluetooth.BluetoothGattDescriptor
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder
import fr.isen.eugene.foodofeugene.Model.BLEService
import fr.isen.eugene.foodofeugene.R


class DetailDeviceAdapter(private val serviceList: MutableList<BLEService>, private val gatt: BluetoothGatt?, private val context: Context?):
        ExpandableRecyclerViewAdapter<DetailDeviceAdapter.ServiceViewHolder, DetailDeviceAdapter.CharacteristicsViewHolder>
        (serviceList) {

    class CharacteristicsViewHolder(itemView: View): ChildViewHolder(itemView) {

        val characteristicSpecific: TextView = itemView.findViewById(R.id.characteristiquespecifique)
        val characteristicUUID: TextView = itemView.findViewById(R.id.uuid)
        val characteristicProperties: TextView = itemView.findViewById(R.id.proprietes)
        val characteristicValue: TextView = itemView.findViewById(R.id.valeur)

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
            holder.characteristicRead.visibility = View.GONE
            holder.characteristicWrite.visibility = View.GONE
            holder.characteristicNotify.visibility = View.GONE
            val characteristic: BluetoothGattCharacteristic? = (group as BLEService).items[childIndex]
        if (characteristic != null) {
            if(properties(characteristic.properties).contains("ECRITURE")){
                holder.characteristicWrite.visibility = View.VISIBLE
            }
            if(properties(characteristic.properties).contains("LECTURE")){
                holder.characteristicRead.visibility = View.VISIBLE
            }
            if(properties(characteristic.properties).contains("NOTIFIER")){
                holder.characteristicNotify.visibility = View.VISIBLE
            }
        }
            holder.characteristicRead.setOnClickListener {
                readToDevice(holder, group, childIndex)
            }

            holder.characteristicWrite.setOnClickListener {
                writeToDevice(holder, group, childIndex)
            }

        holder.characteristicNotify.setOnClickListener {
                notifyToDevice(holder, group, childIndex)
        }
    }

    override fun onBindGroupViewHolder(
            holder: ServiceViewHolder,
            flatPosition: Int,
            group: ExpandableGroup<*>) {
            holder.serviceName.text = group.title
            holder.serviceAddress.text = BLEUUIDAttributes.getBLEUuidAttributes(group.title).title

    }

    fun properties(property: Int): StringBuilder{
        val sb = StringBuilder()
        if(property and BluetoothGattCharacteristic.PROPERTY_READ!=0){
            sb.append("LECTURE")
        }
        if(property and BluetoothGattCharacteristic.PROPERTY_WRITE!=0){
            sb.append("ECRITURE")
        }
        if(property and BluetoothGattCharacteristic.PROPERTY_NOTIFY!=0){
            sb.append("Notifier")
        }
        if(sb.isEmpty()) {
            sb.append("Aucune")
        }
        return sb
    }

    enum class BLEUUIDAttributes(val uuid: String, val title: String) {
        GENERIC_ACCESS("00001800-0000-1000-8000-00805f9b34fb", "Accès générique"),
        GENERIC_ATTRIBUTE("00001801-0000-1000-8000-00805f9b34fb", "Attribut générique"),
        CUSTOM_CHARACTERISTIC("466c1234-f593-11e8-8eb2-f2801f1b9fd1", "Caracteristique spécifique"),
        CUSTOM_CHARACTERISTIC_2("466c9abc-f593-11e8-8eb2-f2801f1b9fd1", "Caracteristique spécifique"),
        UNKNOWN_SERVICE("", "Inconnu");

        companion object {
            fun getBLEUuidAttributes(uuid: String) = values().firstOrNull { it.uuid == uuid }
                    ?: UNKNOWN_SERVICE
        }
    }

    private fun readToDevice(holder: CharacteristicsViewHolder, group: ExpandableGroup<*>, childIndex: Int) {
        val characteristic: BluetoothGattCharacteristic? = (group as BLEService).items[childIndex]
        val res = gatt?.readCharacteristic(characteristic)
        Log.d("error", res.toString())
        Log.d("uuid: ", characteristic?.uuid.toString())
        if(characteristic?.value!=null){
            val textReceive = String(characteristic?.value)
            holder.characteristicValue.text = "${textReceive}"
        }
    }

    private fun writeToDevice(holder: CharacteristicsViewHolder, group: ExpandableGroup<*>, childIndex: Int){
        val characteristic: BluetoothGattCharacteristic? = (group as BLEService).items[childIndex]
        val alertDialog = AlertDialog.Builder(context)
        val editView = View.inflate(context, R.layout.ecriture, null)
        alertDialog.setView(editView)
        alertDialog.setPositiveButton("Valider"){ _, _ ->
            val pop: TextView = editView.findViewById(R.id.popup)
            val text = pop.text.toString().toByteArray()
            characteristic?.setValue(text)

            val res1 = gatt?.writeCharacteristic(characteristic)
            Log.d("erreur : ", res1.toString())

            val res = gatt?.readCharacteristic(characteristic)
            Log.d("erreur : ", res.toString())
        }
        alertDialog.setNegativeButton("Annuler") { alertDialog, _ -> alertDialog.cancel() }
        alertDialog.create()
        alertDialog.show()
    }

    private fun notifyToDevice(holder: CharacteristicsViewHolder, group: ExpandableGroup<*>, childIndex: Int): Boolean {
        val characteristic: BluetoothGattCharacteristic? = (group as BLEService).items[childIndex]
        var enabled = false
        if(!enabled){
            enabled = true
            gatt?.setCharacteristicNotification(characteristic, true)
            characteristic?.descriptors?.forEach { descriptor ->
                descriptor?.run {
                    Log.d(TAG, "descriptors.uuid : " + descriptor.uuid)
                    if(characteristic.properties and BluetoothGattCharacteristic.PROPERTY_NOTIFY !=0){
                        Log.d(TAG, "enableNotification PROPERTY_NOTIFY")
                        descriptor.value = if (enabled) BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE else BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE
                    } else if(characteristic.properties and BluetoothGattCharacteristic.PROPERTY_INDICATE !=0){
                        Log.d(TAG, "enableNotification PROPERTY_INDICATE")
                        descriptor.value = if (enabled) BluetoothGattDescriptor.ENABLE_INDICATION_VALUE else BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE
                    }
                    gatt?.writeDescriptor(descriptor)
                }
            }
        } else{
            gatt?.setCharacteristicNotification(characteristic, false)
        }
        return enabled
    }


}