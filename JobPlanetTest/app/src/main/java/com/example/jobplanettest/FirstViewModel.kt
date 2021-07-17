package com.example.jobplanettest

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jobplanettest.db.CompanyInfoDatabase

class FirstViewModel: ViewModel() {
    fun getCount(context: Context) : LiveData<Int> {
        var companyInfoDatabase = CompanyInfoDatabase.getInstance(context)
        var zeroValue = MutableLiveData<Int>().apply {
            value = 0
        }
        return companyInfoDatabase?.companyInfoDao()?.getCount() ?: zeroValue
    }
}