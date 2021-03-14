package fr.isen.eugene.foodofeugene.Model

import com.google.gson.annotations.SerializedName


data class Items(@SerializedName("id")val id: String,
                 @SerializedName("name_fr")val name: String,
                 @SerializedName("prices ")val prices: Prices,
                 @SerializedName("ingredients")val ingredients: List<Ingredients>
)
