package fr.isen.eugene.foodofeugene

import android.content.Intent
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


class CategoryActivity : AppCompatActivity(), CategoryAdapter.onItemClickListener {

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
        binding.categoryList.adapter = CategoryAdapter(listOf("Salade niçoise", "Salade cesar", "Salade italienne"), this)
    }

    override fun onItemClicked(item: String) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("dish", item)
        startActivity(intent)
    }



}




