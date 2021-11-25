package com.sample.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.data.entities.countryInfo.CountryInfo
import com.sample.data.remote.Resource
import com.sample.data.repository.CountriesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor
    (
    private val countriesRepo: CountriesRepo
) : ViewModel() {

    private val TAG = CountriesViewModel::class.java.name


    private val _searchQuery = MutableLiveData<String>()
    val searchQuery: LiveData<String> = _searchQuery

    private val _countries = MutableLiveData<Resource<List<CountryInfo>?>>()
    val countries: LiveData<Resource<List<CountryInfo>?>> = _countries


    /**
     * Make sure to save the data while screen rotation.
     *
     */
    init {
        fetchAndCacheData()
    }

    fun fetchAndCacheData() = viewModelScope.launch {
//        Log.d(TAG, "fetchAndCacheData()")
        _countries.value = Resource.Loading
        _countries.value = countriesRepo.getAllCountries()


    }

    fun setSearchQuery(query: String) {
        this._searchQuery.value = query
    }


}