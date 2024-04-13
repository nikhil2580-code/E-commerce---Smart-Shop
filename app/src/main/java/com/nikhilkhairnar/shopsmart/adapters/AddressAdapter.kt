package com.nikhilkhairnar.shopsmart.adapters

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.nikhilkhairnar.shopsmart.Address
import com.nikhilkhairnar.shopsmart.R
import com.nikhilkhairnar.shopsmart.databinding.AddressRvItemBinding

class AddressAdapter: Adapter<AddressAdapter.AddressViewHolder>(){

    inner class AddressViewHolder(val binding: AddressRvItemBinding):
            ViewHolder(binding.root){
                fun bind(address: Address, isSelected: Boolean){
                    binding.apply {
                        buttonAddress.text = address.addressTitle
                        if(isSelected){
                            buttonAddress.background = ColorDrawable(itemView.context.getColor(R.color.g_blue))
                            //change the color logic for blue
                        }else{
                            // change color into white
                            buttonAddress.background = ColorDrawable(itemView.context.getColor(R.color.white))

                        }

                    }
                }

            }
    private val  diffUtil = object : DiffUtil.ItemCallback<Address>(){
        override fun areItemsTheSame(oldItem: Address, newItem: Address): Boolean {
            return  oldItem.addressTitle == newItem.addressTitle && oldItem.fullName == newItem.fullName
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Address, newItem: Address): Boolean {
            return newItem == oldItem
        }
    }
    val differ = AsyncListDiffer(this,diffUtil)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        return AddressViewHolder(
            AddressRvItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }
    var selectedAddress = -1

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        val address = differ.currentList[position]
        holder.bind(address, selectedAddress == position)

        holder.binding.buttonAddress.setOnClickListener {
            if (selectedAddress >= 0)
                notifyItemChanged(selectedAddress)
            selectedAddress = holder.bindingAdapterPosition
            notifyItemChanged(selectedAddress)
            onClick?.invoke(address)
        }
    }

    init {
        differ.addListListener { _, _ ->
            notifyItemChanged(selectedAddress)
        }
    }
    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    var onClick: ((Address) -> Unit)? = null
}