package com.example.jobplanettest

data class ComapnyListSimpleData(
    var title : String,
    var description: String,
    var totalAvg : Double = 0.0,
    var industryName: String = "N/A",
    var cellTeyp: CellTypeDefine = CellTypeDefine.Company)
