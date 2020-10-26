package com.example.lab6

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class TeamService {

    val teamListLiveData = MutableLiveData<List<Team>>()
    private val client = OkHttpClient()
    @Throws(IOException::class)
    fun run(url: String?,context: Context){
        Thread {
            println("============= call api")
            val request: Request = Request.Builder()
                .url(url)
                .build()
            client.newCall(request).execute().use { response ->
                var jsonText = response.body()!!.string();
                println("====" + jsonText)

                var list: List<Team> = readFromJson(context, jsonText) ?: emptyList();
                println("====list===" + list.size)
                for (item in list) {
                    println(item.toString())

                }

                teamListLiveData.postValue(list)

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