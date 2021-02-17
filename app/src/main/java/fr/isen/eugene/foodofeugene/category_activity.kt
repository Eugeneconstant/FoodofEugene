package fr.isen.eugene.foodofeugene

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable

enum class Type{
    ENTREES, PLATS, DESSERTS;

    companion object  {
        fun categoryTitle(type: Type?): String{
            return when (type){
                ENTREES -> " Entrees"
                PLATS -> " Plats "
                DESSERTS -> " Desserts"
                else -> ""
            }
        }
    }
}



class category_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.category_activity)
    }
}