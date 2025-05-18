package com.example.mvvm.features.favproducts.view

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.R
import com.example.mvvm.databinding.ActivityAllProductsBinding
import com.example.mvvm.databinding.ActivityFavProductsBinding
import com.example.mvvm.features.allproducts.view.AllProductsAdapter
import com.example.mvvm.features.allproducts.viewmodel.AllProductsViewModel
import com.example.mvvm.features.allproducts.viewmodel.AllProductsViewModelFactory
import com.example.mvvm.features.favproducts.viewmodel.FavProductsViewModel
import com.example.mvvm.features.favproducts.viewmodel.FavProductsViewModelFactory
import com.example.mvvm.model.local.ProductLocalDataSourceImpl
import com.example.mvvm.model.local.RoomLocalDB
import com.example.mvvm.model.pojo.Product
import com.example.mvvm.model.remote.IProductService
import com.example.mvvm.model.remote.ProductRemoteDataSourceImpl
import com.example.mvvm.model.remote.RetrofitHelper
import com.example.mvvm.model.repo.ProductRepositoryImpl

class FavProductsActivity : AppCompatActivity() {

    lateinit var binding: ActivityFavProductsBinding
    lateinit var viewModel: FavProductsViewModel
    lateinit var vmFactory: FavProductsViewModelFactory
    lateinit var productsAdapter: FavProductsAdapter

    @SuppressLint("ServiceCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.hasFixedSize()
        val layoutManager : LinearLayoutManager = LinearLayoutManager(this@FavProductsActivity)
        layoutManager.orientation = RecyclerView.VERTICAL
        binding.recyclerView.layoutManager = layoutManager

        vmFactory = FavProductsViewModelFactory(
            ProductRepositoryImpl.getInstance(
            ProductLocalDataSourceImpl(RoomLocalDB.getInstance(this@FavProductsActivity).getProductDao()),
            ProductRemoteDataSourceImpl(RetrofitHelper.retrofit.create(IProductService::class.java))
        ))

        viewModel = ViewModelProvider(this, vmFactory)[FavProductsViewModel::class.java]

        val adapterListener : (Product) -> Unit = {
            viewModel.removeProduct(it)
            viewModel.getAllProducts()
        }

        productsAdapter = FavProductsAdapter(adapterListener)
        productsAdapter.submitList(listOf())

        binding.recyclerView.adapter = productsAdapter

        viewModel.getAllProducts()

        viewModel.onlineProducts.observe(this@FavProductsActivity){ products ->
            productsAdapter.submitList(products)
        }
    }
}