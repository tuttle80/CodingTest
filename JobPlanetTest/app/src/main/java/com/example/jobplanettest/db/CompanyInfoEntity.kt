package com.example.jobplanettest.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class CompanyInfoEntity(
    @PrimaryKey(autoGenerate = true) var id: Long?,
    @ColumnInfo(name = "CellType") var cellType: Int,
    @ColumnInfo(name = "Name") var name: String?,
    @ColumnInfo(name = "RateTotalAvg") var rateTotalAvg: Double,
    @ColumnInfo(name = "IndustryName") var industryName: String?,
){
    constructor(): this(0, 0, "", 0.0, "")
}