package Model

import kotlinx.serialization.SerialName


data class Name(@SerialName("id") val id_name: Int, @SerialName("name_fr") val Nom: String, @SerialName("images")val images: Images, @SerialName("ingredients")val ingredients: Ingredients, val price: Prices)


