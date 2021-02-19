package fr.isen.eugene.foodofeugene

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.isen.eugene.foodofeugene.databinding.ActivityCategoryBinding


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


class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding
    lateinit var listEntrees: RecyclerView
    private var adapter: RecyclerView.Adapter<*>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.categoryList.layoutManager = LinearLayoutManager(this)
        binding.categoryList.adapter = CategoryAdapter(listOf("Salades ", "pate bolo", "Burgers"))

    }
}