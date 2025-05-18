package com.example.mvvm.features.allproducts.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm.model.pojo.Product
import com.example.mvvm.model.repo.IProductRepository
import kotlinx.coroutines.launch

class AllProductsViewModel(private val _iRepo : IProductRepository): ViewModel() {
    private val _productList = MutableLiveData<List<Product>?>()
    val onlineProducts : LiveData<List<Product>?> = _productList

    fun getAllProducts(flag : Boolean)
    {
        try {
            viewModelScope.launch {
                val product = _iRepo.getProducts(flag)
                Log.i("TAG", "getAllProducts: $product")
                _productList.postValue(product)
            }
        }
        catch (ex: Exception) {
            Log.i("TAG", "getAllProducts: ERROR")
        }
    }

    fun insertProduct(product: Product)
    {
        viewModelScope.launch {
            _iRepo.insertSingleProduct(product)
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("TAG", "onCleared: AllProducts ViewModel Cleared")
    }
}