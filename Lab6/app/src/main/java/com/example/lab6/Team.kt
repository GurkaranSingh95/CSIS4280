package com.example.lab6

data class Team(var name: String, var id: String, var imgResource: Int = R.drawable.profile) {
    override fun toString(): String {
        return name + " " + id
    }
}