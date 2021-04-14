package fr.isen.eugene.foodofeugene


import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.eugene.foodofeugene.Model.Items
import fr.isen.eugene.foodofeugene.databinding.CellCategoryBinding


class CategoryAdapter(private val categories: List<Items>, private val clickListener: (Items) -> Unit): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): CategoryViewHolder {
        val binding = CellCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.title.text = categories[position].name
        holder.prix.text = categories[position].getPrice().toString() + " € "
        holder.layout.setOnClickListener {
            clickListener.invoke(categories[position])
        }
        if (categories[position].getFirstPicture().isNullOrEmpty()) {
            Picasso.get()
                    .load("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.bienmanger.com%2F1F35111_Pommes_Story_France_Bio.html&psig=AOvVaw0U_PYhJwo0gmSn9k_mIl-g&ust=1616153284194000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCJC4zKXeue8CFQAAAAAdAAAAABAI")
                    .into(holder.image)
        } else {
            Picasso.get().load(categories[position].getFirstPicture())
                    .into(holder.image)
        }
    }

    override fun getItemCount(): Int = categories.size

    class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.cellCategoryTitle)
        val prix: TextView = view.findViewById(R.id.cellCategoryPrice)
        val layout = view.findViewById<View>(R.id.cellLayout)
        val image: ImageView = view.findViewById((R.id.images_api))
    }
}

