package fr.isen.eugene.foodofeugene.Model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Images(@SerializedName("images") val url: String) : Serializable

