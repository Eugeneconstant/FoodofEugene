package fr.isen.eugene.foodofeugene.Model

import com.google.gson.annotations.SerializedName



data class Category(@SerializedName("name_fr") val name: String, @SerializedName("items")val items: List<Items>)


