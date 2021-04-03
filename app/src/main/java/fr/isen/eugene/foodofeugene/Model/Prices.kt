package fr.isen.eugene.foodofeugene.Model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Prices(@SerializedName("price") val price: String,
                  @SerializedName("id") val id_price: String) : Serializable

