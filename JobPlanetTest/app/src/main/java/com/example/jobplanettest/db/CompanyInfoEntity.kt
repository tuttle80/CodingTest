package com.example.jobplanettest.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


//@Entity(tableName = "user")
@Entity
class CompanyInfoEntity(
    @PrimaryKey(autoGenerate = true) var id: Long?,
    @ColumnInfo(name = "DateTime") var dateTime: Long,
    @ColumnInfo(name = "RawData") var rawData: String?,

//    @ColumnInfo(name = "EMail") var email: String,
//    @ColumnInfo(name = "Password") var password: String
){
    constructor(): this(0, 0, "")
}