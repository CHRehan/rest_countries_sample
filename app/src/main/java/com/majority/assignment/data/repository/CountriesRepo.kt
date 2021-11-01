package com.majority.assignment.data.repository


import com.google.gson.Gson
import com.majority.assignment.data.entities.countryInfo.Countries
import com.majority.assignment.data.entities.countryInfo.CountryInfo
import com.majority.assignment.data.local.pref.DataStoreManager
import com.majority.assignment.api.CountryService
import com.majority.assignment.data.remote.Resource
import com.majority.assignment.data.remote.SafeApiCall
import com.majority.assignment.utilities.Constants.Companion.FETCH_INTERVAL_IN_SEC
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class CountriesRepo @Inject constructor(

    private val dataStoreManager: DataStoreManager,
    private val countryService: CountryService
) : SafeApiCall {




    private suspend fun getAllCountriesFromDataStore(): List<CountryInfo> {
        val data: String = dataStoreManager.getCountriesJSONString()

        return Gson().fromJson(data, Countries::class.java).countries
    }


    suspend fun getAllCountries(): Resource<List<CountryInfo>> {

        /**
         * Check if the data exist on the Cache or not.
         * Refresh the data according to the FETCH_INTERVAL_IN_SEC
         * defined in Constants.
         *
         */
        if (isFetchNeeded(
                dataStoreManager.getLastFetchTime().first()
            )
        ) {
            val response: Resource<List<CountryInfo>> = safeApiCall {
                countryService.getAllCountries()
            }

            if (response is Resource.Success) {
                /**
                 * Check the status and save the time & data in local cache
                 * Room or DataStore Preference.
                 * We don't need to perform any operations on this static data
                 * So we can save this on preference Datastore.
                 * For this we need to convert the JSON to String.
                 */
                response.let {
                    val now = System.currentTimeMillis() / 1000L
                    dataStoreManager.saveLastFetchTime(now.toInt())
                    (it.value as ArrayList<CountryInfo>).sort()
                    val dataString: String = Gson().toJson(Countries(it.value))
                    dataStoreManager.saveCountriesJSONString(dataString)
                }
            }
            return response

        } else {
            return safeApiCall {
                getAllCountriesFromDataStore()
            }
        }


    }

    private fun isFetchNeeded(lastFetchTime: Int): Boolean {

        // first fetch
        if (lastFetchTime == 0) {
            return true
        }
        val currentTime = System.currentTimeMillis() / 1000L
        if (currentTime - lastFetchTime >= FETCH_INTERVAL_IN_SEC) {
            return true
        }
        return false
    }


}