package com.example.lab6

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class DataService() {

    private val client = OkHttpClient()

    val dataListLiveData = MutableLiveData<List<Data>>()


    @Throws(IOException::class)
    fun run(url: String?, context: Context) {
        Thread {
            println("============= call api")
            val request: Request = Request.Builder()
                .url(url)
                .build()
            client.newCall(request).execute().use { response ->
                var jsonText = response.body()!!.string();
                println("====" + jsonText)

                var list: List<Data> = readFromJson(context, jsonText) ?: emptyList();
                println("====datalist===" + list.size)
                for (item in list) {
                    println(item.toString())
                }
                dataListLiveData.postValue(list);
//                return response.body()!!.string()
            }
        }.start()

    }

    private val myType = Types.newParameterizedType(List::class.java, Data::class.java)

    fun readFromJson(context: Context, jsonText: String): List<Data>? {
        val moshi: Moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<List<Data>> = moshi.adapter(myType)
        val list = adapter.fromJson(jsonText)

        return list;
    }
}