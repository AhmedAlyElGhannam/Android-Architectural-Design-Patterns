package com.example.mvvm

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvm.databinding.MainBinding
import com.example.mvvm.features.allproducts.view.AllProductsActivity
import com.example.mvvm.features.favproducts.view.FavProductsActivity
import com.example.mvvm.model.local.ProductDao
import com.example.mvvm.model.local.ProductLocalDataSourceImpl
import com.example.mvvm.model.local.RoomLocalDB
import com.example.mvvm.model.pojo.Product
import com.example.mvvm.model.remote.IProductService
import com.example.mvvm.model.remote.ProductRemoteDataSourceImpl
import com.example.mvvm.model.remote.RetrofitHelper
import com.example.mvvm.model.repo.ProductRepositoryImpl
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.allProducts.setOnClickListener {
            val intent = Intent(this, AllProductsActivity::class.java)
            startActivity(intent)
        }

        binding.favProducts.setOnClickListener {
            val intent = Intent(this, FavProductsActivity::class.java)
            startActivity(intent)
        }

        binding.exit.setOnClickListener {
            finish()
        }
    }
}