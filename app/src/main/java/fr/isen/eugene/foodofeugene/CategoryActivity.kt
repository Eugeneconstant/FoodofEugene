package fr.isen.eugene.foodofeugene

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import fr.isen.eugene.foodofeugene.HomeActivity.Companion.CATEGORY_NAME
import fr.isen.eugene.foodofeugene.databinding.ActivityCategoryBinding
import org.json.JSONObject


enum class Type{
    ENTREES, PLATS, DESSERTS;

    companion object  {
        fun categoryTitle(type: Type?): String{
            return when (type){
                ENTREES -> "Entrees"
                PLATS -> "Plats"
                DESSERTS -> "Desserts"
                else -> ""
            }
        }
    }
}

class CategoryActivity : AppCompatActivity(), CategoryAdapter.onItemClickListener {

    private lateinit var binding: ActivityCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val categoryName = Type.categoryTitle(intent.getSerializableExtra(CATEGORY_NAME) as Type)
        binding.categoryTitle.text = categoryName
        //binding.categoryList.layoutManager = LinearLayoutManager(this)
        //binding.categoryList.adapter = CategoryAdapter(listOf("Salade niçoise", "Salade cesar", "Salade italienne"), this)
        if (categoryName != null) {
            getData(categoryName)
        }
    }
    override fun onItemClicked(item: String) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("dish", item)
        startActivity(intent)
    }

    private fun getData(category: String?){

        val url = "http://test.api.catering.bluecodegames.com/menu"
        val RequestView = Volley.newRequestQueue(this)
        val  DataJSON = JSONObject().put("id_shop", 1)
        val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, url, DataJSON,
                { it ->
                    Log.d("Response", it.toString())
                },
                { error ->
                    error.printStackTrace()
                }
        )
        RequestView.add(jsonObjectRequest)
    }

}






