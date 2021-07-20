package com.example.jobplanettest

import android.content.Context
import android.util.Log
import androidx.appcompat.widget.AppCompatTextView
import com.example.jobplanettest.db.CompanyInfoEntity
import com.example.jobplanettest.db.CompanyInfoRepo
import com.example.jobplanettest.restapi.JobPlanetAPI
import com.example.jobplanettest.restapi.JobPlanetCompanyInfoList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestApiUtil {
    fun RefreshCompanyInfo(context: Context) {
        /* 새로운 객체를 생성, id 이외의 값을 지정 후 DB에 추가 */
      //  CoroutineScope(Dispatchers.IO).launch {

            // Get Json data
            val BASE_URL_API = "https://jpassets.jobplanet.co.kr/"

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val api = retrofit.create(JobPlanetAPI::class.java)
            val callGetSearchNews = api.getTestData()

            callGetSearchNews.enqueue(object : Callback<JobPlanetCompanyInfoList> {
                override fun onResponse(
                    call: Call<JobPlanetCompanyInfoList>,
                    response: Response<JobPlanetCompanyInfoList>
                ) {
                    Log.d("BugFix", "성공 : ${response.raw()}" + " / " + response.code())


                    val responseList = ArrayList<CompanyInfoEntity>()

                    response.body()?.let {
                        var index : Long = 0
                        for (item in it.items) {
                            var info = CompanyInfoEntity()


                            info.cellType = when (item.cell_type) {
                                "CELL_TYPE_COMPANY" -> CellTypeDefine.Company.ordinal
                                "CELL_TYPE_HORIZONTAL_THEME" -> CellTypeDefine.HorizontalTheme.ordinal
                                "CELL_TYPE_REVIEW" -> CellTypeDefine.Review.ordinal
                                else -> CellTypeDefine.Unknown.ordinal
                            }

                            info.name = item.name
                            info.rateTotalAvg = item.rate_total_avg
                            info.industryName = item.industry_name

                            info.id = index


                            index++

                            responseList.add(info)
                        }
                    }




                    //response.body().
                    CoroutineScope(Dispatchers.IO).launch {

                        // Update Repo
                        val companyInfoRepo = CompanyInfoRepo()

                        companyInfoRepo.refreshRawData(context, responseList)

                    }.start()
                }

                override fun onFailure(call: Call<JobPlanetCompanyInfoList>, t: Throwable) {
                    Log.d("BugFix:", "실패 : $t")
                }
            })






    //    }.start()
    }
}