package com.sample.utilities

class Constants {


    companion object {
        private const val SECONDS_IN_MINUTE = 60
        private const val SECONDS_IN_HOUR = SECONDS_IN_MINUTE * 60
        private const val SECONDS_IN_DAY = SECONDS_IN_HOUR * 24

        const val FETCH_INTERVAL_IN_SEC: Int = SECONDS_IN_DAY
    }
}