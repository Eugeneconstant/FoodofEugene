package Model

import kotlinx.serialization.SerialName

data class Prices(@SerialName("price") val price: Int, @SerialName("id") val id_price: Int)

