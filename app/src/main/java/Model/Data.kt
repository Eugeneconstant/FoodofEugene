package Model

import kotlinx.serialization.SerialName


data class Data(@SerialName("name_fr") val name: String, @SerialName("items")val items: Name)

