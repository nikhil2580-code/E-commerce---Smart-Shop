package com.nikhilkhairnar.shopsmart.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nikhilkhairnar.shopsmart.Product
import com.nikhilkhairnar.shopsmart.R
import com.nikhilkhairnar.shopsmart.databinding.BestProductBinding


class BestProductAdapter(private val navController: NavController): RecyclerView.Adapter<BestProductAdapter.BestProductViewHolder>(){

    inner class BestProductViewHolder (private val binding: BestProductBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(product: Product){
            binding.apply {



                val imageResourceId = product.images.firstOrNull() ?: R.drawable.product_img
                Glide.with(itemView)
                    .load(imageResourceId)
                    .error(R.drawable.product_img)
                    .into(imageBestProduct)
                    product.offerPercentage?.let {
                    val remainingPricePercentage = 1f - it
                    val priceAfterOffer = remainingPricePercentage * product.price
                    tvNewPrices.text = "$ ${String.format("%.2f",priceAfterOffer)}"
                    tvPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

                }
                if(product.offerPercentage == null)
                    tvNewPrices.visibility = View.INVISIBLE
               tvPrice.text = "$ ${product.price}"
                tvName.text = product.name

            }
        }
    }

    private val  diffCallback = object  : DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }


    }
    private val  differ  = AsyncListDiffer(this,diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestProductViewHolder {
        return BestProductViewHolder(
            BestProductBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )

    }

    override fun onBindViewHolder(holder: BestProductViewHolder, position: Int) {
        val product = differ.currentList[position]
        holder.bind(product)
        holder.itemView.setOnClickListener {
            val bundle = bundleOf(
                "product" to product,
                "productImages" to product.images.toIntArray()
            )
            onClick?.invoke(product)
        }

    }

    override fun getItemCount(): Int {
        return  differ.currentList.size
    }

   fun submitList(products: List<Product>){
       differ.submitList(products)
   }

    var onClick : ((Product) -> Unit)? = null
}