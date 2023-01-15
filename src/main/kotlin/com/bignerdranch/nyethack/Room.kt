package com.bignerdranch.nyethack

open class Room(val name: String ) {
    protected open val dangerLevel = 5
    fun description() = "Room: $name\n" + "" +
            "Danger level: $dangerLevel"
    open fun load() = "Noting much see here..."

}

