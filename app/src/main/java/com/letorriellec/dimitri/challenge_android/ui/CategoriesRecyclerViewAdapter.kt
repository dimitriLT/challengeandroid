package com.letorriellec.dimitri.challenge_android.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.letorriellec.dimitri.challenge_android.R
import com.letorriellec.dimitri.challenge_android.model.CategoryViewModel
import kotlinx.android.synthetic.main.category_list_item.view.*

class CategoriesRecyclerViewAdapter : RecyclerView.Adapter<ViewHolder>() {
    
    private var categories = ArrayList<CategoryViewModel>()

    fun setData(items: List<CategoryViewModel>) {
        categories = ArrayList(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.category_list_item, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.id.text = categories[position].id
        holder.resourceType.text = categories[position].resourceType
        holder.resourceUri.text = categories[position].resourceUri
        holder.name.text = categories[position].name
        holder.categoryViewModel = categories[position]
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    lateinit var categoryViewModel: CategoryViewModel

    val id: TextView = view.categoryIdTextView
    val resourceUri: TextView = view.categoryResourcesUriTextView
    val resourceType: TextView = view.categoryResourceTypeTextView
    val name: TextView = view.categoryNameTextView

    init {
        view.setOnClickListener {
            val intent = Intent(view.context, SubCategoryActivity::class.java)
            intent.putExtra("category", categoryViewModel)
            view.context.startActivity(intent)
        }
    }

}