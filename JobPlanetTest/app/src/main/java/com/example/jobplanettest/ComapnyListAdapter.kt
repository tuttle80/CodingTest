package com.example.jobplanettest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView

class ComapnyListAdapter (val context: Context, var mList: ArrayList<ComapnyListSimpleData>) :
    RecyclerView.Adapter<ComapnyListAdapter.CustomViewHolder>() {

    inner class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        protected var name = view.findViewById<AppCompatTextView>(R.id.name)
        protected var totalAvg = view.findViewById<AppCompatTextView>(R.id.rate_total_avg)
        protected var industryName = view.findViewById<AppCompatTextView>(R.id.industry_name)

        fun bind(simpleData: ComapnyListSimpleData) {
            name.text = simpleData.title
            totalAvg.text = simpleData.totalAvg.toString()
            industryName.text = simpleData.industryName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutView = LayoutInflater.from(context)
            .inflate(R.layout.recycleritem_company, parent, false)

        return CustomViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    fun setData(list: ArrayList<ComapnyListSimpleData>) {
        mList = list
    }

    override fun getItemCount(): Int {
        return mList.size
    }

}