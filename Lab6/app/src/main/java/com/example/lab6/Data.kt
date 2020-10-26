package com.example.lab6

data class Data(
    val first_name: String,
    val last_name: String,
    val pet: String,
    val possible: List<Possible>
) {
    override fun toString(): String {
        return first_name + " " + last_name + " " + possible.toString()
    }
}

data class Possible(
    val name: String
) {
    override fun toString(): String {
        return name
    }
}
