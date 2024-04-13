package com.nikhilkhairnar.shopsmart.adapters

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.nikhilkhairnar.shopsmart.R
import com.nikhilkhairnar.shopsmart.databinding.OrderItemBinding
import com.nikhilkhairnar.shopsmart.order.Order
import com.nikhilkhairnar.shopsmart.order.OrderStatus
import com.nikhilkhairnar.shopsmart.order.getOrderStatus

class AllOrdersAdapter: Adapter<AllOrdersAdapter.OrderViewHolder>() {

    inner class OrderViewHolder(private val binding: OrderItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("ResourceAsColor")
        fun bind(order: Order) {
            binding.apply {
                tvOrderId.text = order.orderId
                tvOrderDate.text = order.date

                val colorDrawable = when(getOrderStatus(order.orderStatus)){
                    is OrderStatus.Ordered -> {
                        ColorDrawable(R.color.g_orange_yellow)

                    }
                    is OrderStatus.Confirmed -> {
                        ColorDrawable(R.color.green)
                    }
                    is OrderStatus.Delivered-> {
                        ColorDrawable(R.color.green)
                    }
                    is OrderStatus.Shipped -> {
                        ColorDrawable(R.color.green)
                    }
                    is OrderStatus.Canceled -> {
                        ColorDrawable(R.color.g_red)
                    }
                    is OrderStatus.Returned -> {
                        ColorDrawable(R.color.g_red)
                    }
                }


            }


        }

    }

    private val diffCallback = object : DiffUtil.ItemCallback<Order>() {
        override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean {
            return oldItem.products == newItem.products
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        return OrderViewHolder(
            OrderItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = differ.currentList[position]
        holder.bind(order)

        holder.itemView.setOnClickListener{
            onClick?.invoke(order)
        }



    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    var onClick: ((Order) -> Unit)? = null

}
