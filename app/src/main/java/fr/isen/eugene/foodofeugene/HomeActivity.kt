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
            StartCategoryActivity(Type.ENTREES)
        }

        binding.btn2.setOnClickListener {
            StartCategoryActivity(Type.PLATS)
        }

        binding.btn3.setOnClickListener {
            StartCategoryActivity(Type.DESSERTS)
        }

        binding.ble.setOnClickListener {
            StartBLEScanActivity(Type.BLE)
        }

    }

    private fun StartBLEScanActivity(type: Type){
        val intent = Intent(this, BLEScanActivity::class.java)
        startActivity(intent)
    }

    private fun StartCategoryActivity(type: Type){
        //Log.d("Home activity destroy", "destruction")
        val intent = Intent(this, CategoryActivity::class.java)
        intent.putExtra(CATEGORY_NAME, type)
        startActivity(intent)
    }
    companion object {
        const val CATEGORY_NAME = "CATEGORY_NAME"
    }
}



