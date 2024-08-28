package com.example.havadurumu.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.example.havadurumu.data.repository.CityRepository
import com.example.havadurumu.data.service.ApiClient
import com.example.havadurumu.data.service.ApiServices

class CityViewModel(val repository: CityRepository) :ViewModel(){
    constructor():this(CityRepository(ApiClient().getClient().create(ApiServices::class.java)))





}