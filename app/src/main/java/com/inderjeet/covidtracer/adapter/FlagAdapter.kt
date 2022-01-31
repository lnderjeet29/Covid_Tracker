package com.inderjeet.covidtracer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.inderjeet.covidtracer.R
import com.inderjeet.covidtracer.databinding.FragmentFlagItemDetailBinding
import com.inderjeet.covidtracer.model.AllCovidData

class FlagAdapter(val selectItem:(String)->Unit):ListAdapter<AllCovidData,FlagAdapter.viewholder>(CovidDiff) {
    object CovidDiff:DiffUtil.ItemCallback<AllCovidData>() {
        override fun areItemsTheSame(oldItem: AllCovidData, newItem: AllCovidData): Boolean {
            return oldItem.countryInfo._id == newItem.countryInfo._id
        }

        override fun areContentsTheSame(oldItem: AllCovidData, newItem: AllCovidData): Boolean {
            return oldItem == newItem
        }

    }

    class viewholder (val binding:FragmentFlagItemDetailBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return viewholder(FragmentFlagItemDetailBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        val covid=getItem(position)
        holder.binding.apply {

            bttmImFlag.load(covid.countryInfo.flag){
                placeholder(R.drawable.ic_placeholder)
                error(R.drawable.ic_placeholder)
            }
            bttmCountryName.text=covid.country
            rcItem.setOnClickListener {
                selectItem.invoke(covid.country)
            }
        }
    }
}