package com.example.lab6

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton

import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.view2_layout.view.*
import org.json.JSONArray

class DataAdapter(private val teamList: List<Data>,private val context: Context) : RecyclerView.Adapter<DataAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {


        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view2_layout,parent,false)




        return DataViewHolder(itemView)



    }

    override fun getItemCount(): Int {

        return teamList.size
    }



    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val currentItem = teamList[position]

        holder.first?.setText(currentItem.first_name)
        holder.last?.setText(currentItem.last_name)
        holder.pet?.setText(currentItem.pet)
        holder.possible?.setText(currentItem.possible.toString())
        holder.editButton?.setOnClickListener {

            val intent = Intent(context, AddEmptyActivity::class.java)
            context.startActivity(intent)


        }

    }




    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val first: TextView? = itemView.fName
        val last: TextView? = itemView.lName
        val pet: TextView? = itemView.petName
        val possible: TextView? = itemView.possible
        val editButton:ImageButton? = itemView.editButton


    }

}
