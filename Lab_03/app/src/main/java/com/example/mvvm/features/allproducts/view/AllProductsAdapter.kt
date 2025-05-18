package com.example.mvvm.features.allproducts.view

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvm.databinding.AllItemBinding
import com.example.mvvm.model.pojo.Product
import com.example.mvvm.utils.ProductDiffUtill


class AllProductsAdapter(var myListener : (Product) -> Unit):
    ListAdapter<Product, AllProductsAdapter.ProductViewHolder>(ProductDiffUtill())
{
    lateinit var context :Context

    lateinit var binding : AllItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater:LayoutInflater=LayoutInflater.from(parent.context)
        context= parent.context
        binding = AllItemBinding.inflate(inflater, parent, false)
        return ProductViewHolder(binding)


    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentObj = getItem(position)
        holder.binding.title.text = currentObj.title
        holder.binding.price.text = currentObj.price.toString()
        holder.binding.description.text = currentObj.description
        holder.binding.ratingBar.rating = currentObj.rating.toFloat()
//        holder.binding.rec.setOnClickListener {
//            myListener.invoke(currentObj)
//        }
        holder.binding.delBtn.setOnClickListener {
            myListener.invoke(currentObj)
        }
        Glide.with(context).load(currentObj.thumbnail).into(holder.binding.imgv);
    }

    class ProductViewHolder(var binding: AllItemBinding) : RecyclerView.ViewHolder(binding.root)
}