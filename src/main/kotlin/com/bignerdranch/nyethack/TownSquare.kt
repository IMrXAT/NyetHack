package com.bignerdranch.nyethack

open class TownSquare : Room("Town Square"){
    override val dangerLevel: Int = super.dangerLevel - 3
    private var bellSound : String = "RA-TA-TA-TA"

    fun ringBell() = "The bell tower announces your arrival. $bellSound"
    final override fun load() = "The villagers rally and cheer as you enter!\n${ringBell()}"
}
