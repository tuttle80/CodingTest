package com.example.jobplanettest.db

import android.content.Context
import android.util.Log
import java.nio.charset.StandardCharsets.UTF_8
import java.security.MessageDigest

class CompanyInfoRepo {


//    private fun md5(str: String): ByteArray = MessageDigest.getInstance("MD5").digest(str.toByteArray(UTF_8))
//    private fun ByteArray.toHex() = joinToString("") { "%02x".format(it) }

    fun refreshRawData(context: Context, jsonRawData: String) {
        val newData = CompanyInfoEntity()
        newData.rawData = "12345"
        newData.lastUpdatedDT = System.currentTimeMillis()

        var companyInfoDatabase = CompanyInfoDatabase.getInstance(context)
        val companyInfoDao = companyInfoDatabase?.companyInfoDao()

        // Replace or new insert data
        companyInfoDao?.let {
            val count = companyInfoDao.getCount() ?: 0
            if (count != 0) {
                companyInfoDao.deleteAll()
            }
            companyInfoDao.insert(newData)

            // TODO 왜 타임 sleep 이 발생하지????
      //      Log.d("BugFix", "Data Count : " + companyInfoDao.getCountNormal())

        }

    }

    fun clearData(context: Context) {
        var companyInfoDatabase = CompanyInfoDatabase.getInstance(context)
        companyInfoDatabase?.companyInfoDao()?.deleteAll();
    }


//
//    fun isExistAccount(context: Context, email: String) : Boolean {
//        var userDatabase = CompanyInfoDatabase.getInstance(context)
//
//        val count = userDatabase?.userDao()?.isExistAccount(email) ?: 0
//        return count == 1
//    }
//
//    fun isValidAccount(context: Context, email: String, password: String) : Boolean {
//        var userDatabase = CompanyInfoDatabase.getInstance(context)
//        val pwdMD5 = md5(password).toHex()
//
//        val count = userDatabase?.userDao()?.isValidAccount(email, pwdMD5) ?: 0
//        return count == 1
//    }
}