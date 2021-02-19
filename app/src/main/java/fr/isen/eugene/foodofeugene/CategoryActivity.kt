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
        val categoryName = intent.getStringExtra("categoryTitle")
        binding.categoryTitle.text = categoryName
        binding.categoryList.layoutManager = LinearLayoutManager(this)
        binding.categoryList.adapter = CategoryAdapter(listOf("Salade Cesar ", "Salade nicoise", "Salade Italienne"))
        binding.categoryListPrice.layoutManager = LinearLayoutManager(this)
        binding.categoryListPrice.adapter = CategoryAdapter(listOf("5.90 €", "5.90 €", "5.90 €"))
    }
}