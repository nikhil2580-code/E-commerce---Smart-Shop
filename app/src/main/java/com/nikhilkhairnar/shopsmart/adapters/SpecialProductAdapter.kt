package com.nikhilkhairnar.shopsmart.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nikhilkhairnar.shopsmart.Product
import com.nikhilkhairnar.shopsmart.R
import com.nikhilkhairnar.shopsmart.databinding.SpecialProductBinding


class SpecialProductAdapter(private val navController: NavController): RecyclerView.Adapter<SpecialProductAdapter.SpecialProductViewHolder>() {

inner class SpecialProductViewHolder(private val binding:SpecialProductBinding):
    RecyclerView.ViewHolder(binding.root){
     fun bind(product: Product){
         binding.apply {


             val imageResourceId = product.images.firstOrNull() ?: R.drawable.product_img
             Glide.with(itemView)
                 .load(imageResourceId)
                 .error(R.drawable.product_img)
                 .into(imageSpecilaProduct)
             tvSpecialProductName.text = product.name
             tvSpecialProductPrice.text = "$${product.price}"

         }
     }

}

    private val diffCallback = object: DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
                 return  oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }

    }
    private val  differ  = AsyncListDiffer(this,diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialProductViewHolder {
        return SpecialProductViewHolder(SpecialProductBinding.inflate(LayoutInflater.from(parent.context),parent, false
        )
        )
    }

    override fun onBindViewHolder(holder: SpecialProductViewHolder, position: Int) {
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


