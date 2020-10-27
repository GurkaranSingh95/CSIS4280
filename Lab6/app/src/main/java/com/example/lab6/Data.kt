package com.example.lab6

data class Data(
    public final var first_name: String,
    var last_name: String,
    var pet: String,
    var possible: List<Possible>
) {
    override fun toString(): String {
        return "\n" + first_name + "\n" + last_name + "\n" + " " + pet + "\n" + possible.toString() + "\n"
    }
}

data class Possible(
    public final var name: String
) {
    override fun toString(): String {
        return  name
    }
}
