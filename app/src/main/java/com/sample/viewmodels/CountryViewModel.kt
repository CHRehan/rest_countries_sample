package com.sample.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.sample.data.entities.countryInfo.CountryInfo
import com.sample.extensions.prettyCount
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor() : ViewModel() {
    val countryInfo = ObservableField<CountryInfo>()


    /**
     * This is a simple method to create a paragraph about
     * the selected country. We can remove this in future if we need to design
     * any view display the country info.
     */
    fun getCountryDetail(): String {
        val countryInfo = countryInfo.get()!!
        val stringBuilder = StringBuilder()
        stringBuilder.append(countryInfo.name?.common)
        stringBuilder.append(" officially known as ")
        stringBuilder.append(countryInfo.name?.official)
        stringBuilder.append(" located in the region of ")
        stringBuilder.append(countryInfo.region)
        stringBuilder.append(" sub region is ")
        stringBuilder.append(countryInfo.subregion + ".")
        stringBuilder.append("Total population of this country is ")
        stringBuilder.append(countryInfo.population?.prettyCount())
        stringBuilder.append(". ")
        stringBuilder.append(countryInfo.name?.official)
        if (countryInfo.unMember!!)
            stringBuilder.append(" is a UN member country. ")
        else
            stringBuilder.append(" is unfortunately not a UN member country yet. ")

        countryInfo.capital?.let {
            stringBuilder.append("Capital(s) of this country is ")

            for ((i, capital) in it.withIndex()) {
                stringBuilder.append(capital)
                if (it.size > 1 && i < it.size - 1)
                    stringBuilder.append(" , ")
            }
            stringBuilder.append(".")

        }


        if (countryInfo.currencies.isNotEmpty())

            countryInfo.currencies.keys.let {

                stringBuilder.append(" The currency of ")
                stringBuilder.append(countryInfo.name?.official)
                stringBuilder.append(" is ")


                for ((i, key) in it.withIndex()) {
                    val currencyName = countryInfo.currencies[key]?.name
                    val currencySymbol = countryInfo.currencies[key]?.symbol

                    stringBuilder.append(currencyName)
                    stringBuilder.append(" and symbol is ")
                    stringBuilder.append(currencySymbol)
                    if (it.size > 1 && i < it.size - 1)
                        stringBuilder.append(" , ")

                }
                stringBuilder.append(".")
            }


        if (countryInfo.languages.isNotEmpty())
            countryInfo.languages.keys.let {
                stringBuilder.append("\nMost common language(s) are ")

                for ((i, key) in it.withIndex()) {
                    val language = countryInfo.languages[key]

                    stringBuilder.append(language)
                    if (it.size > 1 && i < it.size - 1)
                        stringBuilder.append(" , ")

                }
                stringBuilder.append(".")
            }


        if (countryInfo.continents.isNotEmpty())

            countryInfo.continents.let {
                stringBuilder.append(" This country located in ")

                for ((i, value) in it.withIndex()) {

                    stringBuilder.append(value)
                    if (it.size > 1 && i < it.size - 1)
                        stringBuilder.append(" , ")

                }
                stringBuilder.append(" continent(s).")

            }

        if (countryInfo.timezones.isNotEmpty())

            countryInfo.timezones.let {
                stringBuilder.append(" Timezone(s) of this sub region is ")

                for ((i, value) in it.withIndex()) {

                    stringBuilder.append(value)
                    if (it.size > 1 && i < it.size - 1)
                        stringBuilder.append(" , ")

                }
                stringBuilder.append(". ")

            }

        stringBuilder.append(countryInfo.startOfWeek?.uppercase())
        stringBuilder.append(" is the start day of the week.")


        return stringBuilder.toString()

    }
}