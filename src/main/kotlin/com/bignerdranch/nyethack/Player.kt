package com.bignerdranch.nyethack

import java.io.File
import java.util.*

class Player(_name: String,
             var healthPoints: Int = 100,
             var isBlessed: Boolean,
             var isImmortal: Boolean,
             val race : String){

    var name: String = _name
        get() = "${field.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }} of $hometown"
        set(value) {
            field = value.trim()
        }

    val hometown : String by lazy {selectHometown()}
    var currentPosition = Coordinate(0, 0)


    init {
        require(healthPoints > 0) { "healthPoints must be greater that zero." }
        require(name.isNotBlank()) {"player must have name."}
    }

    constructor(name: String) : this(name,
        isBlessed = true,
        isImmortal = false,
        race = "ork")

    fun castFireball(numFireballs: Int = 2){
        println("A glass of Fireball springs into existence. (x$numFireballs)")
    }

     fun auraColor() = if (isBlessed && healthPoints > 50 || isImmortal) "GREEN" else "NONE"


    private fun selectHometown() = File("data/towns")
            .readText()
            .split("\n")
            .random()



    fun formatHealthStatus(): String {

        val healthStatus = when (healthPoints) {
            100 -> "is in excellent condition!"
            in 90..99 -> "has a few scratches."
            in 75..89 -> if (isBlessed) {
                "has some minor wounds but is healing quite quickly!"
            } else {
                "has some minor wounds."
            }

            in 15..74 -> "looks pretty hurt."
            else -> "is in awful condition!"
        }
        return healthStatus
    }

    fun showDrunkLevel(drunkLevel : Int){
        println("you drunk on $drunkLevel")
        when (drunkLevel){
            in 1..10 -> println("Tipsy")
            in 11..20 -> println("Sloshed")
            in 21..30 -> println("Soused")
            in 31..40 -> println("Stewed")
            in 41..50 -> println("..t0aSt3d")
        }
    }

    private fun findMyFaction(race: String): String {
        val faction: String = when (race) {
            "dwarf" -> "Keepers of the Mines"
            "gnome" -> "Keepers of the Mines"
            "orc" -> "Free People of the Rolling Hills"
            "human" -> "Free People of the Rolling Hills"
            else -> ""
        }
        return faction
    }

}