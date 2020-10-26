package com.example.lab6

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    private val teamListLiveData = MutableLiveData<List<Team>>()
//    val studentNames = ArrayList<String>(
//        Arrays.asList(
//            "Tushit Nag Kanuri",
//            "Nam Phan",
//            "Gukaran Singh",
//            "Agam Choudhary"
//        )
//    )
//    val studentID =
//        ArrayList<String>(Arrays.asList("300298577", "300298577", "300298577", "300298577"))

    val teamList = ArrayList<Team>()

    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        run("http://54.145.163.174:5000/team")

        recyclerView.adapter = TeamAdapter(teamList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

//        teamListLiveData.observe(this, Observer { ms ->
//            ms?.let {
//                println("LIST: " + it.size)
//                if (it?.size > 0) {
//                }
//            }
//        })

    }

    @Throws(IOException::class)
    fun run(url: String?) {
        Thread {
            println("============= call api")
            val request: Request = Request.Builder()
                .url(url)
                .build()
            client.newCall(request).execute().use { response ->
                var jsonText = response.body()!!.string();
                println("====" + jsonText)

                var list: List<Team> = readFromJson(this, jsonText) ?: emptyList();
                println("====list===" + list.size)
                for (item in list) {
                    println(item.toString())
                }
                teamListLiveData.postValue(list);
//                return response.body()!!.string()
            }
        }.start()

    }

    private val myType = Types.newParameterizedType(List::class.java, Team::class.java)

    fun readFromJson(context: Context, jsonText: String): List<Team>? {
        val moshi: Moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<List<Team>> = moshi.adapter(myType)
        val list = adapter.fromJson(jsonText)

        return list;
    }
}