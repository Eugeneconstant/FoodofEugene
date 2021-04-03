package fr.isen.eugene.foodofeugene.Model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Items(@SerializedName("id") val id: String,
                 @SerializedName("name_fr") val name: String,
                 @SerializedName("prices") val prices: List<Prices>,
                 @SerializedName("ingredients") val ingredients: List<Ingredients>,
                 @SerializedName("images") val images: List<String>
): Serializable{

    fun getPrice() = prices[0].price + "€"

    fun getFirstPicture() = if (images.isNotEmpty() && images[0].isNotEmpty()){
        images[0]
    } else {
        null
    }
    fun getIngredients(): String = ingredients.map(Ingredients::name_fr).joinToString( " , " )
}
