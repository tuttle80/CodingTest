package com.example.jobplanettest.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CompanyInfoEntity::class], version = 1)
abstract class CompanyInfoDatabase : RoomDatabase() {
    abstract fun companyInfoDao(): CompanyInfoDao

    companion object {
        private var INSTANCE: CompanyInfoDatabase? = null

        fun getInstance(context: Context): CompanyInfoDatabase? {
            if (INSTANCE == null) {
                synchronized(CompanyInfoDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        CompanyInfoDatabase::class.java, "comapnyinfo.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

}