package fr.isen.eugene.foodofeugene

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.isen.eugene.foodofeugene.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn1.setOnClickListener {
            SCA(Type.ENTREES)
        }

        binding.btn2.setOnClickListener {
            SCA(Type.PLATS)
        }

        binding.btn3.setOnClickListener {
            SCA(Type.DESSERTS)
        }

    }

    private fun SCA(type: Type){
        val intent = Intent(this, category_activity::class.java)
        intent.putExtra(CATEGORY_NAME, type)
        startActivity(intent)
    }
    companion object {
        const val CATEGORY_NAME = "CATEGORY_NAME"
    }
}



