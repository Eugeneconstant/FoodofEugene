package fr.isen.eugene.foodofeugene

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.synnapps.carouselview.ImageListener
import fr.isen.eugene.foodofeugene.Model.Items
import fr.isen.eugene.foodofeugene.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private var itemCount = 1
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val detail = intent.getSerializableExtra("items") as? Items
        if(detail!=null){
            binding.itemTitle.text = detail.name
            binding.ingredientsTextView.text = detail.getIngredients()
            binding.carousel.pageCount = detail.images.size
            binding.detailPrice.text = "Price : " + detail.getPrice().toString() + "€"
            binding.countitem.text = itemCount.toString()
            val imageListener: ImageListener = object : ImageListener {
                override fun setImageForPosition(position: Int, imageView: ImageView?) {
                    if(detail.images[0].isNullOrEmpty()){
                        Picasso.get().load("http://www.estdebol.ru/images/no_photo.png").into(imageView)
                    }else{
                        Picasso.get().load(detail.images[position]).into(imageView)
                    }
                }
            }
            binding.carousel.setImageListener(imageListener)
        }

        binding.plus.setOnClickListener {
            itemCount++
            binding.countitem.text = itemCount.toString()
            if(detail !=null)
                getTotalPrice(detail, itemCount)
        }

        binding.moins.setOnClickListener {
            itemCount--
            binding.countitem.text = itemCount.toString()
            if(detail !=null)
                    getTotalPrice(detail, itemCount)
        }

    }
    private fun getTotalPrice(detail: Items, quantity: Int){
        val total = quantity * detail.getPrice()
        "Price :  $total €".also {
            binding.detailPrice.text = it
        }
    }
}

