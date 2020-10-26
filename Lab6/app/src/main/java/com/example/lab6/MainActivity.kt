package com.example.lab6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

        val studentNames = ArrayList<String>(Arrays.asList("Tushit Nag Kanuri","Nam Phan", "Gukaran Singh", "Agam Choudhary"))
        val studentID = ArrayList<String>(Arrays.asList("300298577","300298577","300298577","300298577"))

        val teamList = ArrayList<Team>()

        private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //AddData()
        run("http://54.145.163.174:5000/team")

        recyclerView.adapter = TeamAdapter(teamList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)



    }


    fun AddData()
    {
        for(i in studentNames.indices) //we use .indices when we want to use index
        {
            val item = Team(R.drawable.profile,studentNames[i],studentID[i])

            teamList.add(item)
        }
    }
    fun run(url: String) {
        val request = Request.Builder()
            .url(url)
            .build()
println("============= call api")
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) = println(response.body()?.string())
        })
    }

}