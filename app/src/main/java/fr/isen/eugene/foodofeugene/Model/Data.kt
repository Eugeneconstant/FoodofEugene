package fr.isen.eugene.foodofeugene.Model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Data(@SerializedName("data")val data: List<Category>) : Serializable


