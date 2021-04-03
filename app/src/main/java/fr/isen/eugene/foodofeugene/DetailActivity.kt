package fr.isen.eugene.foodofeugene

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.synnapps.carouselview.ImageListener
import fr.isen.eugene.foodofeugene.Model.Items
import fr.isen.eugene.foodofeugene.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

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
    }
}