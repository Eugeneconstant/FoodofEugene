package fr.isen.eugene.foodofeugene

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.eugene.foodofeugene.HomeActivity.Companion.CATEGORY_NAME
import fr.isen.eugene.foodofeugene.Model.Data
import fr.isen.eugene.foodofeugene.Model.Items
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
        val categoryName = intent.getSerializableExtra(HomeActivity.CATEGORY_NAME) as? Type
        binding.categoryTitle.text = getcategorytitle(categoryName)
        //binding.categoryList.layoutManager = LinearLayoutManager(this)
        //binding.categoryList.adapter = CategoryAdapter(listOf("Salade cesar", "Salade italienne", "Salade nicoise"), this)
        getData(getcategorytitle(categoryName))
    }
    override fun onItemClicked(item: String) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("dish", item)
        startActivity(intent)
    }

    private fun getcategorytitle(item: Type?): String{
        return when (item){
            Type.ENTREES -> "Entrées"
            Type.PLATS -> "Plats"
            Type.DESSERTS -> "Desserts"
            else -> ""
        }
    }
    private fun getData(category: String?){

        val url = "http://test.api.catering.bluecodegames.com/menu"
        val RequestView = Volley.newRequestQueue(this)
        val DataJSON = JSONObject().put("id_shop", 1)
        val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, url, DataJSON,
                { it ->
                    Log.d("Response", it.toString())
                    val menu = Gson().fromJson(it.toString(), Data::class.java)
                    displayMenu(menu)
                },
                { error ->
                    Toast.makeText(applicationContext, "Something wrong. Try Again!", Toast.LENGTH_SHORT).show()
                    error.printStackTrace()
                }
        )
        RequestView.add(jsonObjectRequest)
    }

    private fun displayMenu(menu: Data) {
        val categoryTitleList = menu.data[0].items
        binding.categoryList.layoutManager = LinearLayoutManager(this)
        binding.categoryList.adapter = CategoryAdapter(categoryTitleList, this)
    }

}






