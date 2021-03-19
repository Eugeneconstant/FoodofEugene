package fr.isen.eugene.foodofeugene

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.eugene.foodofeugene.Model.Items
import fr.isen.eugene.foodofeugene.databinding.ActivityDetailBinding

class DetailActivity(private val item: Items) : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.ingredientsTextView.text = item?.getIngredients()
        binding.itemTitle.text = item?.name
    }

}