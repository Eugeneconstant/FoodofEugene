package fr.isen.eugene.foodofeugene


import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.eugene.foodofeugene.Model.Items
import fr.isen.eugene.foodofeugene.databinding.CellCategoryBinding


class CategoryAdapter(private val categories: List<Items>, private val clickListener: CategoryActivity): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): CategoryViewHolder {
        val binding  = CellCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.title.text = categories[position].name
        holder.Price.text = categories[position].prices.price
        holder.layout.setOnClickListener{
            //clickListener.onItemClicked(categories[position].name)
        }

    }

    override fun getItemCount(): Int = categories.size

    class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.cellCategoryTitle)
        //val Image: ImageView = view.findViewById(R.id.cellCategoryImage)
        val Price: TextView = view.findViewById(R.id.cellCategoryPrice)
        val layout = view.findViewById<View>(R.id.cellLayout)
    }



    interface onItemClickListener{
        fun onItemClicked(item: Items)
    }
    
}

