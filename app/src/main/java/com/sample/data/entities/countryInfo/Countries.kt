package com.sample.data.entities.countryInfo

/**
 * Contain primary constructor with at least one parameter.
 * Parameters of primary constructor marked as val or var.
 * Data class cannot be abstract, inner, open or sealed.
 */
data class Countries(
    val countries: List<CountryInfo>
)