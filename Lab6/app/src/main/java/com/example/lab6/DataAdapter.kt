package com.example.lab6

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view1_layout.view.*
import kotlinx.android.synthetic.main.view2_layout.view.*
import org.json.JSONArray

class DataAdapter(private val teamList: List<Data>) : RecyclerView.Adapter<DataAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {

//        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view1_layout,parent,false)
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view2_layout,parent,false)




        return DataViewHolder(itemView)

    }

    override fun getItemCount(): Int {

        return teamList.size
    }



    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val currentItem = teamList[position]

        holder.first?.setText(currentItem.firstName)
        holder.last?.setText(currentItem.lastName)
        holder.pet?.setText(currentItem.pet)
//        holder.possible?.setText(currentItem.possible)
    }




    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val first: TextView? = itemView.fName
        val last: TextView? = itemView.lName
        val pet: TextView? = itemView.petName
        val possible: TextView? = itemView.possible


    }
}
