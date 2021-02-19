package fr.isen.eugene.foodofeugene


import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CategoryAdapter(val Categories: List<String>): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_category, parent, false)

        return CategoryViewHolder(view)
    }




    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.title.text = Categories[position]
    }

    override fun getItemCount(): Int = Categories.size

    class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.cellCategoryTitle)
    }
}

