package fr.isen.eugene.foodofeugene.ble

import android.bluetooth.BluetoothProfile
import androidx.annotation.StringRes
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup
import fr.isen.eugene.foodofeugene.R


enum class BLEConnexionState(val state: Int, @StringRes val text: Int) {
    STATE_CONNECTING(BluetoothProfile.STATE_CONNECTING, R.string.ble_device_status_connecting),
    STATE_CONNECTED(BluetoothProfile.STATE_CONNECTED, R.string.ble_device_status_connected),
    STATE_DISCONNECTING(BluetoothProfile.STATE_CONNECTING, R.string.ble_device_status_disconnecting),
    STATE_DISCONNECTED(BluetoothProfile.STATE_CONNECTING, R.string.ble_device_status_disconnected);

    companion object {
        fun getBLEConnexionStateFromState(state: Int) = values().firstOrNull {
            it.state == state
        }
    }


}