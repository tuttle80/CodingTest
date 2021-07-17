package com.example.jobplanettest.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.jobplanettest.ComapnyListAdapter


@Dao
interface CompanyInfoDao {


    @Query("SELECT COUNT(*) FROM CompanyInfoEntity")
    fun getCount(): LiveData<Int>

    @Insert(onConflict = REPLACE)
    fun insert(companyInfo: CompanyInfoEntity)

    @Query("DELETE from CompanyInfoEntity")
    fun deleteAll()





}