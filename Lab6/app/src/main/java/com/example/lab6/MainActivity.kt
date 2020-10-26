package com.example.lab6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

        val studentNames = ArrayList<String>(Arrays.asList("Tushit Nag Kanuri","Nam Phan", "Gukaran Singh", "Agam Choudhary"))
        val studentID = ArrayList<String>(Arrays.asList("300298577","300298577","300298577","300298577"))

        val teamList = ArrayList<Team>()

        val dataList = ArrayList<Data>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AddData()

//         val tuneTabs: TabLayout = findViewById(R.id.tabLayout);

        println("Datalist$dataList")


        recyclerView.adapter = DataAdapter(dataList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

/*        recyclerView.adapter = TeamAdapter(teamList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)*/
    }


    fun AddData()
    {

        for (i in studentNames.indices)
        {
            val item = Data(studentNames[i],studentNames[i],studentID[i],studentID[i])

            println("ITEM : " + item.firstName + item.lastName + item.pet + item.possible)
            dataList.add(item)



        }
        /*
        for(i in studentNames.indices) //we use .indices when we want to use index
        {
            val item = Team(R.drawable.profile,studentNames[i],studentID[i])

            teamList.add(item)
        }*/


    }


}