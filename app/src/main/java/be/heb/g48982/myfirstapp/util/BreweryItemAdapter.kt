package be.heb.g48982.myfirstapp.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import be.heb.g48982.myfirstapp.databinding.ListViewItemBinding
import be.heb.g48982.myfirstapp.network.BreweryItem

/**
 * Adapter class used by recyclerView
 */
class BreweryItemAdapter(private val clickListener : BreweryItemListener) : ListAdapter<BreweryItem,BreweryItemAdapter.ViewHolder>(BreweryItemDiffCallback()) {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!,clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }
    class ViewHolder private constructor(val binding: ListViewItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BreweryItem, clickListener: BreweryItemListener) {
            binding.brewery = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListViewItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }
    class BreweryItemDiffCallback : DiffUtil.ItemCallback <BreweryItem>(){
        override fun areItemsTheSame(oldItem: BreweryItem, newItem: BreweryItem): Boolean {
            return oldItem.id ==newItem.id
        }

        override fun areContentsTheSame(oldItem: BreweryItem, newItem: BreweryItem): Boolean {
            return oldItem == newItem
        }
    }
    class BreweryItemListener(val clickListener:(brewItemId : String?) ->Unit ){
        fun onClick(breweryItem: BreweryItem) = clickListener(breweryItem.id)
    }


}

