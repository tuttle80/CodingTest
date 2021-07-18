package com.example.jobplanettest.restapi

data class JobPlanetCompanyInfoList (

    var total_page: Long = 0,
    var total_count: Long = 0,
    var items: List<Items>
//    "minimum_interviews": 0,
//    "total_page": 31763,
//    "minimum_reviews": 0,
//    "total_count": 317624,
)

data class Items(
    var cell_type: String = "",
    var name: String = "",
)