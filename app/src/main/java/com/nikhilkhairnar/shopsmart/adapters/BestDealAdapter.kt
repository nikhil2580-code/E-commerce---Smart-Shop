package com.nikhilkhairnar.shopsmart.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.nikhilkhairnar.shopsmart.Product
import com.nikhilkhairnar.shopsmart.R
import com.nikhilkhairnar.shopsmart.databinding.BestDealProductBinding


class BestDealAdapter(private val navController: NavController):RecyclerView.Adapter<BestDealAdapter.BestDealViewHolder>(){

    inner class BestDealViewHolder(private val binding: BestDealProductBinding ):
        ViewHolder(binding.root){
        fun bind(product: Product){
       binding.apply {



           val imageResourceId = product.images.firstOrNull() ?: R.drawable.product_img
           Glide.with(itemView).
           load(imageResourceId)
               .error(R.drawable.product_img).
               into(bestDealImages)
           product.offerPercentage?.let {
               val remainingPricePercentage = 1f - it
               val priceAfterOffer = remainingPricePercentage * product.price
               tvNewPrice.text = "$ ${String.format("%.2f",priceAfterOffer)}"

           }
           tvOldPrice.text = "$ ${product.price}"
           tvDealProductName.text = product.name
       }
        }
    }

    private val  diffCallback = object  :DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
           return oldItem == newItem
        }


    }
    private val  differ  = AsyncListDiffer(this,diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestDealViewHolder {
        return BestDealViewHolder(
        BestDealProductBinding.inflate(
        LayoutInflater.from(parent.context)
           )
        )

    }

    override fun onBindViewHolder(holder: BestDealViewHolder, position: Int) {
        val product = differ.currentList[position]
        holder.bind(product)

        holder.itemView.setOnClickListener{
            onClick?.invoke(product)
        }
    }

    override fun getItemCount(): Int {
        return  differ.currentList.size
    }

    fun submitList(products: List<Product>){
        differ.submitList(products)
    }
    var onClick: ((Product) -> Unit)? = null

}