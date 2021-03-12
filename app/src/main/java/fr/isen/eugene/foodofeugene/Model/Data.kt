package fr.isen.eugene.foodofeugene.Model

import com.google.gson.annotations.SerializedName



data class Data(@SerializedName("data")val data: List<Category>)


