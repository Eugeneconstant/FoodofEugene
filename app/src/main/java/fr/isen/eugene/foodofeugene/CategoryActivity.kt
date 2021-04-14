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
import com.google.gson.GsonBuilder
import fr.isen.eugene.foodofeugene.HomeActivity.Companion.CATEGORY_NAME
import fr.isen.eugene.foodofeugene.Model.Data
import fr.isen.eugene.foodofeugene.Model.Items
import fr.isen.eugene.foodofeugene.Type.Companion.categoryTitle
import fr.isen.eugene.foodofeugene.databinding.ActivityCategoryBinding
import org.json.JSONObject
import fr.isen.eugene.foodofeugene.DetailActivity as DetailActivity


enum class Type{
    ENTREES, PLATS, DESSERTS, BLE;
    companion object  {
        fun categoryTitle(type: Type?): String{
            return when (type){
                ENTREES -> "Entrees"
                PLATS -> "Plats"
                DESSERTS -> "Desserts"
                BLE -> "BLE"
                else -> ""
            }
        }
    }
}

class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val categoryName = intent.getSerializableExtra(CATEGORY_NAME) as Type?
        binding.categoryTitle.text = categoryTitle(categoryName)
        getData(getCategorytitle(categoryName))
    }
    private fun getCategorytitle(item: Type?): String{
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
                    displayMenu(it.toString(), category)
                },
                { error ->
                    Toast.makeText(applicationContext, "Something wrong. Try Again!", Toast.LENGTH_SHORT).show()
                    error.printStackTrace()
                }
        )
        RequestView.add(jsonObjectRequest)
    }
    private fun displayMenu(res: String, categoryTitle: String?) {
        val dataResult = GsonBuilder().create().fromJson(res, Data::class.java)
        val items = dataResult.data.firstOrNull{ it.name == categoryTitle }
        loadList(items?.items)
    }
    private fun loadList(items: List<Items>?) {
        items?.let {
            val adapter = CategoryAdapter(it) { item ->
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("items", item)
                startActivity(intent)
            }
            binding.categoryList.layoutManager = LinearLayoutManager(this)
            binding.categoryList.adapter = adapter
        }
    }
}








