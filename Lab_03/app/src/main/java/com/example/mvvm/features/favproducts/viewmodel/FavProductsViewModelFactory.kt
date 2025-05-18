package com.example.mvvm.features.favproducts.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.model.repo.IProductRepository

class FavProductsViewModelFactory(private val repo: IProductRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavProductsViewModel(repo) as T
    }
}