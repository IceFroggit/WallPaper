package icefroggit.app.domain.paging


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import icefroggit.app.R
import icefroggit.app.databinding.ItemLoaderBinding

class LoadStateViewHolder(
    private val binding: ItemLoaderBinding,
    retry: () -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.retryButtonItem.setOnClickListener {
            retry.invoke()
        }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.errorMsg.text = "please try again later"
        }
        binding.progressBar.isVisible = loadState is LoadState.Loading
        binding.retryButtonItem.isVisible = loadState !is LoadState.Loading
        binding.errorMsg.isVisible = loadState !is LoadState.Loading
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): LoadStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_loader, parent, false)
            val binding = ItemLoaderBinding.bind(view)
            return LoadStateViewHolder(binding, retry)
        }
    }
}