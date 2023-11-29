package com.gautamtuteja.a7minuteworkout

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.gautamtuteja.a7minuteworkout.databinding.ItemExcersiceStatusBinding

class ExcersiceStatusAdapter(val item : ArrayList<ExcerModel>)
    : RecyclerView.Adapter<ExcersiceStatusAdapter.ViewHolder>(){


        class ViewHolder(binding: ItemExcersiceStatusBinding)
            :RecyclerView.ViewHolder(binding.root){

                val tvItem = binding.tvItem
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder{
        return ViewHolder(ItemExcersiceStatusBinding.inflate(
            LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model : ExcerModel = item[position]
        holder.tvItem.text=model.getId().toString()


        when{
            model.getSlected() ->{
                holder.tvItem.background =
                    ContextCompat.getDrawable(holder.itemView.context ,
                    R.drawable.item_circular_thin_color_accent_border)
                holder.tvItem.setTextColor(Color.parseColor("#212121"))
            }
            model.getComplete() ->{
                holder.tvItem.background =
                    ContextCompat.getDrawable(holder.itemView.context ,
                        R.drawable.item_circular_color_accent_bg)
                holder.tvItem.setTextColor(Color.parseColor("#FFFFFF"))
            }
            else -> {
                holder.tvItem.background =
                    ContextCompat.getDrawable(holder.itemView.context ,
                        R.drawable.item_circular_gray_bg)
                holder.tvItem.setTextColor(Color.parseColor("#212121"))
            }
        }

    }

    override fun getItemCount(): Int {
        return item.size
    }



}