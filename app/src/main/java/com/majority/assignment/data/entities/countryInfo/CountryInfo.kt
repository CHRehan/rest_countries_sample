package com.majority.assignment.data.entities.countryInfo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/*[
  {
    "name": {
      "common": "Malaysia",
      "official": "Malaysia"
    },
    "unMember": true,
    "currencies": {
      "MYR": {
        "name": "Malaysian ringgit",
        "symbol": "RM"
      }
    },
    "capital": [
      "Kuala Lumpur"
    ],
    "region": "Asia",
    "subregion": "South-Eastern Asia",
    "languages": {
      "eng": "English",
      "msa": "Malay"
    },
    "population": 32365998,
    "timezones": [
      "UTC+08:00"
    ],
    "continents": [
      "Asia"
    ],
    "flags": {
      "png": "https://flagcdn.com/w320/my.png"
    },
    "startOfWeek": "sunday"
  }
]*/

@Parcelize

//An empty constructor is compulsory for Room
class CountryInfo() : Parcelable, Comparable<CountryInfo> {

    var id: Int? = null

    var name: Name? = null
    var unMember: Boolean? = null
    var capital: ArrayList<String>? = null
    var region: String? = null
    var subregion: String? = null
    var languages: HashMap<String, String> = HashMap()
    var currencies: HashMap<String, CurrencyDetail> = HashMap()
    var population: Long? = null
    var timezones: ArrayList<String> = ArrayList()
    var continents: ArrayList<String> = ArrayList()
    var flags: Flags? = null
    var startOfWeek: String? = null


    override fun compareTo(other: CountryInfo): Int {
        return name?.official?.let {
            other.name?.official?.compareTo(it)
        } ?: kotlin.run { return 0 }
    }


}