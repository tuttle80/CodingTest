package com.example.jobplanettest.restapi

data class JobPlanetCompanyInfoList (
    var minimum_interviews: Int = 0,
    var minimum_reviews: Int = 0,
    var total_page: Long = 0,
    var total_count: Long = 0,
    var page: Int = 0,
    var page_size: Int = 0,
    var minimum_salaries: Int = 0,

    var items: List<Items>
)

data class Items(
    var ranking: Int = 0,
    var cell_type : String = "",
    var interview_difficulty: Double = 0.0,
    var name : String = "",
    var salary_avg: Int = 0,
    var web_site : String = "",
    var logo_path : String = "",
    var interview_question : String = "",
    var company_id: Long =  0,
    var has_job_posting: Boolean = true,
    var rate_total_avg: Double = 0.0,
    var industry_id: Int = 0,
    var review_summary : String = "",
    var type : String = "",
    var industry_name : String = "",
    var simple_desc : String = "",

    var count: Int = 0,
    var themes: List<Themes>
)

data class Themes(
    var color: String = "",
    var cover_image: String = "",
    var id: Int = 0,
    var title : String = ""
 )