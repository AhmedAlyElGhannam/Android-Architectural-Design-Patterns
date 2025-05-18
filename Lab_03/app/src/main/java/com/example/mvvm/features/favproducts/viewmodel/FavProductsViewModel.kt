package com.example.mvvm.features.favproducts.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm.model.pojo.Product
import com.example.mvvm.model.repo.IProductRepository
import kotlinx.coroutines.launch

class FavProductsViewModel(private val _iRepo : IProductRepository): ViewModel() {
    private val _productList = MutableLiveData<List<Product>?>()
    val onlineProducts : LiveData<List<Product>?> = _productList

    fun getAllProducts()
    {
        viewModelScope.launch {
            val product = _iRepo.getProducts(false)
            Log.i("TAG", "getAllProducts: $product")
            _productList.postValue(product)
        }
    }

    fun removeProduct(product: Product)
    {
        viewModelScope.launch {
            _iRepo.removeSingleProduct(product)
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("TAG", "onCleared: AllProducts ViewModel Cleared")
    }
}