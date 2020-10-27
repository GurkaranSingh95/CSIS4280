package com.example.lab6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.add_data_layout.*

class AddEmptyActivity : AppCompatActivity() {

    var editData = Data("", "", "", emptyList())
    var possibleList: Array<String> = emptyArray()
    var possibleData = Possible("")
    var posList: MutableList<Possible> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_empty)

        createUpdateButton.setOnClickListener {

            addData()
        }


    }

    fun addData() {

       
        editData.first_name = fNameEdit.text.toString()
        editData.last_name = lNameEdit.text.toString()
        editData.pet = petEdit.text.toString()
        possibleData.name = possibleEditText.text.toString()

        possibleList = possibleData.name.split(",").toTypedArray()



        println("List of Strings" + possibleList.toString() + "\n")


        for (i in possibleList.indices) {
            var possible = Possible("")

            possible.name = possibleList[i]

            posList.add(possible)
        }

        editData.possible = posList

        println("==================PossibleData" + editData.possible.toString() + "\n")
    }
}