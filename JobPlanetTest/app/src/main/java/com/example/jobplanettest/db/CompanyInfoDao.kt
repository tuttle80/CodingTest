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
    fun getCountNormal(): Int

    @Insert(onConflict = REPLACE)
    fun insert(companyInfo: ArrayList<CompanyInfoEntity>)

    @Query("DELETE from CompanyInfoEntity")
    fun deleteAll()

    @Query("SELECT * FROM CompanyInfoEntity")
    fun getListAll() : LiveData< Array<CompanyInfoEntity> >


    @Query("SELECT COUNT(*) FROM CompanyInfoEntity")
    fun getCount(): LiveData<Int>

//    @Query("SELECT MAX(LastUpdatedDT) FROM CompanyInfoEntity")
//    fun getLastUpdateDT(): LiveData<Long>




}