package com.example.lab6

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view1_layout.view.*

class TeamAdapter(private var teamList: List<Team>) :
    RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {

        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.view1_layout, parent, false)
        return TeamViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {

        val currentItem = teamList[position]
        holder.studentName.text = currentItem.name
        holder.studentID.text = currentItem.id


    }

    override fun getItemCount(): Int {
        return teamList.size
    }


    class TeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val studentName: TextView = itemView.memberName

        val studentID: TextView = itemView.memberID

    }

    internal fun setItems(data: List<Team>) {
        this.teamList = data
        notifyDataSetChanged()
    }


}