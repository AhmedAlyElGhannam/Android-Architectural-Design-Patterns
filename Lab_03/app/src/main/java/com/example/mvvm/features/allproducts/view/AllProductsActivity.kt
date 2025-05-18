package com.example.mvvm.features.allproducts.view

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.R
import com.example.mvvm.databinding.ActivityAllProductsBinding
import com.example.mvvm.databinding.MainBinding
import com.example.mvvm.features.allproducts.viewmodel.AllProductsViewModel
import com.example.mvvm.features.allproducts.viewmodel.AllProductsViewModelFactory
import com.example.mvvm.model.local.ProductLocalDataSourceImpl
import com.example.mvvm.model.local.RoomLocalDB
import com.example.mvvm.model.pojo.Product
import com.example.mvvm.model.remote.IProductService
import com.example.mvvm.model.remote.ProductRemoteDataSourceImpl
import com.example.mvvm.model.remote.RetrofitHelper
import com.example.mvvm.model.repo.ProductRepositoryImpl

class AllProductsActivity : AppCompatActivity() {

    lateinit var binding: ActivityAllProductsBinding
    lateinit var viewModel: AllProductsViewModel
    lateinit var vmFactory: AllProductsViewModelFactory
    lateinit var productsAdapter: AllProductsAdapter

    @SuppressLint("ServiceCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.hasFixedSize()
        val layoutManager : LinearLayoutManager = LinearLayoutManager(this@AllProductsActivity)
        layoutManager.orientation = RecyclerView.VERTICAL
        binding.recyclerView.layoutManager = layoutManager



        vmFactory = AllProductsViewModelFactory(ProductRepositoryImpl.getInstance(
            ProductLocalDataSourceImpl(RoomLocalDB.getInstance(this@AllProductsActivity).getProductDao()),
            ProductRemoteDataSourceImpl(RetrofitHelper.retrofit.create(IProductService::class.java))
        ))

        viewModel = ViewModelProvider(this, vmFactory)[AllProductsViewModel::class.java]


        val adapterListener : (Product) -> Unit = {
            viewModel.insertProduct(it)
            Toast.makeText(this@AllProductsActivity, "Product added to favourites", Toast.LENGTH_SHORT).show()
        }

        productsAdapter = AllProductsAdapter(adapterListener)
        productsAdapter.submitList(listOf())

        binding.recyclerView.adapter = productsAdapter

        val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.activeNetwork?.let {
            connectivityManager.getNetworkCapabilities(it)
        }
        val isConnected = networkCapabilities?.let {
            it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
        } ?: false

        viewModel.getAllProducts(isConnected)

        viewModel.onlineProducts.observe(this@AllProductsActivity){ products ->
            productsAdapter.submitList(products)
        }
    }
}