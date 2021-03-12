package Model

import kotlinx.serialization.SerialName

data class Ingredients(@SerialName("id") val id : Int,
                       @SerialName("name_fr") val name_fr : String)
