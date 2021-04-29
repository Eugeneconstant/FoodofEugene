package fr.isen.eugene.foodofeugene.Model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Device(@SerializedName("message") val  message: String = "Characterisation specifique",
                  @SerializedName("uuid") val uuid: String,
                  @SerializedName("proprietes") val proprietes: String,
                  @SerializedName("value") val value: String,
                  @SerializedName("expand") val expand: Boolean = false) : Serializable
