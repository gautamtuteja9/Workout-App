package com.gautamtuteja.a7minuteworkout

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.gautamtuteja.a7minuteworkout.databinding.ItemHistoryRowBinding

class HistoryAdapter(private val items : ArrayList<String>):
    RecyclerView.Adapter<HistoryAdapter.viewHolder>() {


    class viewHolder(binding: ItemHistoryRowBinding)
        :RecyclerView.ViewHolder(binding.root){
        val llhisItemMain = binding.llHisItem
        val tvItem = binding.tvItem
        val tvpos = binding.position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        return viewHolder(ItemHistoryRowBinding.inflate(
            LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val date : String = items.get(position)
        holder.tvpos.text = (position+1).toString()
        holder.tvItem.text =date

        if(position%2 == 0){
            holder.llhisItemMain.setBackgroundColor(Color.parseColor("#EBEBEB"))
        }else{
            holder.llhisItemMain.setBackgroundColor(Color.parseColor("#FFFFFF"))
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

}