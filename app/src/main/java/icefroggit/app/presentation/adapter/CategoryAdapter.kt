package icefroggit.app.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import icefroggit.app.R
import icefroggit.app.databinding.CategoryItemRowBinding
import icefroggit.app.domain.model.Category

class CategoryAdapter(
    private val category: List<Category>,
    private val listener: CategoryInteractionListener
) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {


    inner class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = CategoryItemRowBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_item_row, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val currentCategory = category[position]
        holder.binding.apply {
            categoryTextView.text = currentCategory.categoryName

            Glide.with(holder.itemView.context)
                .load(currentCategory.imageUrl)
                .centerCrop()
                .error(R.color.teal_200)
                .into(categoryImageView)
        }
        holder.itemView.setOnClickListener {
            listener.onClickCategory(currentCategory, it)
        }
    }


    override fun getItemCount() = category.size
}