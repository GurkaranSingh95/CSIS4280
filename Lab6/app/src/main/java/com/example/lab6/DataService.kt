package com.example.lab6

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.io.IOException


class DataService() {

    private val client = OkHttpClient()

    val dataListLiveData = MutableLiveData<List<Data>>()
    var dataList: List<Data> = arrayListOf();


    @Throws(IOException::class)
    fun run(url: String?, context: Context) {
        Thread {
            println("============= call api")
            val request: Request = Request.Builder()
                .url(url)
                .build()
            client.newCall(request).execute().use { response ->
                try {
                    var jsonText = response.body()!!.string();
                    println("====" + jsonText)

                    var list: List<Data> = readFromJson(context, jsonText) ?: emptyList();
                    println("====datalist===" + list.size)
                    for (item in list) {
                        println(item.toString())
                    }
                    dataList = list;
                    dataListLiveData.postValue(list);
                } catch (e: Exception) {

                }

            }
        }.start()

    }

    fun addNew(url: String, data: Data) {

        dataList += data;

        this.callUpdateApi(url);
    }

    val JSON: MediaType = MediaType.get("application/json; charset=utf-8");

    @Throws(IOException::class)
    fun callUpdateApi(url: String?) {
        Thread {
            var json = jsonToString(dataList);
            println("=====Data=====" + json);
            val body: RequestBody = RequestBody.create(JSON, json)
            val request: Request = Request.Builder()
                .url(url)
                .post(body)
                .build()
            client.newCall(request).execute().use { response ->
                {
                    println(response.message())
                }
            }
        }.start()
    }

    private val myType =
        Types.newParameterizedType(List::class.java, Data::class.java)

    fun readFromJson(context: Context, jsonText: String): List<Data>? {
        val moshi: Moshi = Moshi.Builder()
            .build()
        val adapter: JsonAdapter<List<Data>> = moshi.adapter(myType)
        val list = adapter.fromJson(jsonText)

        return list;
    }

    fun jsonToString(list: List<Data>): String {
        var jsonString = "";
        val gsonPretty = GsonBuilder().setPrettyPrinting().create()
        jsonString = gsonPretty.toJson(list.toList())
        println(jsonString)
        return jsonString;
    }
}
