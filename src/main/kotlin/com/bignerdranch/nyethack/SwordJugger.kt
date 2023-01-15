package com.bignerdranch.nyethack

import java.lang.Exception

fun main(){
    var swordsJuggling : Int? = null
    val isJugglingProficient = (1..3).random() == 3
    if (isJugglingProficient) swordsJuggling = 2
    try{
        proficiencyCheck(swordsJuggling)
        swordsJuggling = swordsJuggling!!.plus(1)
    }
    catch (e: Exception){
        println(e)
    }
    println("you juggle $swordsJuggling swords")
}

fun proficiencyCheck(swordsJugging: Int?){
//    swordsJugging ?: throw UnskilledSwordJugglerException()
    checkNotNull(swordsJugging) { "com.bignerdranch.nyethack.Player cannot juggle swords" }
}
