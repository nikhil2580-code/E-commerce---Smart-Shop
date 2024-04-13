package com.nikhilkhairnar.shopsmart.adapters

import com.nikhilkhairnar.shopsmart.CartProduct
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nikhilkhairnar.shopsmart.PriceCalculator
import com.nikhilkhairnar.shopsmart.databinding.CartProductItemBinding

@Suppress("DEPRECATION")
class CartProductAdapter : RecyclerView.Adapter<CartProductAdapter.CartProductViewHolder>() {
    private  var cartAdapterListener : CartAdapterListener? = null

    fun setCartAdapterListener(listener:CartAdapterListener){
        this.cartAdapterListener = listener
    }

    interface CartAdapterListener {
        fun onItemRemoved(products: List<CartProduct>)
        fun updateTotalPrice(products: List<CartProduct>)
        fun saveProductsToSharedPreferences(products: List<CartProduct>)
    }


    fun getTotalPrice(): Float {
        var totalPrice = 0f
        for (cartProduct in differ.currentList) {
            totalPrice += cartProduct.product.price * cartProduct.quantity
        }
        return totalPrice
    }

    inner class CartProductViewHolder(val binding: CartProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.imagePlus.setOnClickListener {
                val position = adapterPosition
                if(position != RecyclerView.NO_POSITION){
                    onPlusClick?.invoke(differ.currentList[position])

                }
            }
            binding.imageMinus.setOnClickListener {
                val position = adapterPosition
                if(position != RecyclerView.NO_POSITION){
                    onMinusClick?.invoke(differ.currentList[position])
                }
            }

        }

        @SuppressLint("SuspiciousIndentation")
        fun bind(cartProduct: CartProduct) {
            binding.apply {
                Glide.with(itemView)
                    .load(cartProduct.product.images[0])
                    .into(imageCartProduct)
                tvProductCartName.text = cartProduct.product.name
                tvCartProductQuantity.text = cartProduct.quantity.toString()

                val totalPrice = PriceCalculator.calculateTotalPrice(cartProduct)
                tvProductCartPrice.text = "$ ${String.format("%.2f", totalPrice)}"
            }
        }
    }



    fun increaseQuantity(cartProduct: CartProduct){
        val newList = ArrayList(differ.currentList)
        val index = newList.indexOfFirst { it.product.id == cartProduct.product.id }
        if (index != -1 ){
            val currentQuantity = newList[index].quantity
            if(currentQuantity < 10) {
                newList[index] = cartProduct.copy(quantity = currentQuantity + 1)
                differ.submitList(newList)
                notifyItemChanged(index)
            }
        }

    }
    fun decreaseQuantity(cartProduct: CartProduct, holder: CartProductViewHolder) {
        val newList = ArrayList(differ.currentList)
        val index = newList.indexOfFirst { it.product.id == cartProduct.product.id }
        if (index != -1) {
            val currentQuantity = newList[index].quantity
            if (currentQuantity <= 1) {
                // Show alert message and remove the item from the list
                val context = holder.itemView.context
                AlertDialog.Builder(context)
                    .setTitle("Remove Product")
                    .setMessage("Are you sure you want to remove this Product from the cart?")
                    .setPositiveButton("Yes") { _, _ ->
                        newList.removeAt(index)
                        differ.submitList(newList)
                        cartAdapterListener?.updateTotalPrice(newList) // Update total price after removing product
                        cartAdapterListener?.saveProductsToSharedPreferences(newList)
                    }
                    .setNegativeButton("No", null)
                    .show()
            } else {
                // Decrease the quantity
                newList[index] = cartProduct.copy(quantity = currentQuantity - 1)
                differ.submitList(newList)
                notifyItemChanged(index)
                cartAdapterListener?.updateTotalPrice(newList) // Update total price after decreasing quantity
                cartAdapterListener?.saveProductsToSharedPreferences(newList)
            }
        }
    }



    private val diffCallback = object : DiffUtil.ItemCallback<CartProduct>() {
        override fun areItemsTheSame(oldItem: CartProduct, newItem: CartProduct): Boolean {
            return oldItem.product.id == newItem.product.id
        }

        override fun areContentsTheSame(oldItem: CartProduct, newItem: CartProduct): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartProductViewHolder {
        return CartProductViewHolder(
            CartProductItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }
    override fun onBindViewHolder(holder: CartProductViewHolder, position: Int) {
        val cartProduct = differ.currentList[position]
        holder.bind(cartProduct)

        holder.itemView.setOnClickListener {
            onProductClick?.invoke(cartProduct)
        }



    }

    fun addToCart(cartProduct: CartProduct) {
        val newList = ArrayList(differ.currentList)
        newList.add(cartProduct)
        differ.submitList(newList)
    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    fun submitList(list: List<CartProduct>) {
        differ.submitList(list)
    }

    var onProductClick: ((CartProduct) -> Unit)? = null
    var onPlusClick: ((CartProduct) -> Unit)? = null
    var onMinusClick: ((CartProduct) -> Unit)? = null
}