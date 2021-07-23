package com.example.jobplanettest.db

import android.content.Context
import android.util.Log
import java.nio.charset.StandardCharsets.UTF_8
import java.security.MessageDigest

class CompanyInfoRepo {

    fun refreshRawData(context: Context, responseList: ArrayList<CompanyInfoEntity>) {
        var companyInfoDatabase = CompanyInfoDatabase.getInstance(context)
        val companyInfoDao = companyInfoDatabase?.companyInfoDao()

        // Replace or new insert data
        companyInfoDao?.let {
//            val count = companyInfoDao.getCount() ?: 0
//            if (count != 0) {
//                companyInfoDao.deleteAll()
//            }

            companyInfoDao.insert(responseList)
        }

    }

    fun clearData(context: Context) {
        var companyInfoDatabase = CompanyInfoDatabase.getInstance(context)
        companyInfoDatabase?.companyInfoDao()?.deleteAll();
    }
}