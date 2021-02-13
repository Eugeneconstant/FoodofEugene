package fr.isen.eugene.foodofeugene

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import fr.isen.eugene.foodofeugene.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val monIntent: Intent = Intent(this, category_activity::class.java)
        binding.btn1.setOnClickListener {
            startActivity(monIntent)
        }

        binding.btn2.setOnClickListener {
            //Toast.makeText(this, "Hello Toast", Toast.LENGTH_LONG).show()
        }

        binding.btn3.setOnClickListener {
            //Toast.makeText(this, "Hello Toast", Toast.LENGTH_LONG).show()
        }

    }
}