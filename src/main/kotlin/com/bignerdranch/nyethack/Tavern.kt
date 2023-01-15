package com.bignerdranch.nyethack

import java.io.File

const val TAVERN_NAME = "Taernyl's Folly"

val patronList = mutableListOf("Eli", "Mordoc", "Sophie")
val lastName = listOf("Ironfoot", "Fernsworth", "Baggins")
val uniquePatrons = mutableSetOf<String>()
val menuList = File("data/tavern_menu_data")
                                        .readText()
                                        .split("\n")

val patronGold = mutableMapOf<String, Double>()

fun main(){
    printMenu()
    if (patronList.contains("Eli")){
        println("The tavern master says: Eli's in the back playing cards.")
    } else {
        println("The tavern master says: Eli isn't here.")
    }
    if (patronList.containsAll(listOf("Sophie", "Mordoc"))) {
        println("The tavern master says: Yea, they're seated by the stew kettle.")
    } else {
        println("The tavern master says: Nay, they departed hours ago.")
    }
    for (i in 0..9){
        val first = patronList.random()
        val second = lastName.random()
        val name = "$first $second"
        uniquePatrons.add(name)
    }
    uniquePatrons.forEach{
        patronGold[it] = 6.0
    }
    var orderCount = 0
    while (orderCount <= 9){
        placeOrder(uniquePatrons.random(), menuList.random())
        println()
        orderCount++
    }
    displayPatronBalances()
}

fun displayPatronBalances() {
    patronGold.forEach{ (patron, balance) ->
        println("$patron, balance: ${"%.2f".format(balance)}")

    }
}


fun placeOrder(patronName: String, menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("$patronName speaks with $tavernMaster about their order.")

    val (type, name, price) = menuData.split(", ")

    val message = "$patronName buys $name ($type) for $price"
    println(message)
    performPurchase(price.toDouble(), patronName)

    val phrase = when (name) {
        "Dragon`s Breath" -> {
            "$patronName exclaims: ${toDragonTalk("Ah, delicious $name")}"
        }
        else -> {
            "$patronName says: Thanks for $name"
        }
    }

    println(phrase)
}



fun performPurchase(price: Double, patronName: String) {
    val totalPurse = patronGold.getValue(patronName)
    patronGold[patronName] = totalPurse - price
    println("$patronName has left ${"%.2f".format(patronGold[patronName])} gold")
    if (patronGold.getValue(patronName) <= 0){
        patronGold.remove(patronName)
        uniquePatrons.remove(patronName)
        println("$patronName go out on NyetHack outside")
    }
}

fun toDragonTalk(phrase: String) =
    phrase.replace(Regex("[aeiouAEIOU]")){
        when (it.value){
            "a", "A"  -> "4"
            "e", "E"  -> "3"
            "i", "I"  -> "1"
            "o", "O"  -> "0"
            "u", "U"  -> "|_|"
            else -> it.value
        }
    }


fun printMenu(){
    val helloMessage = "*** Welcome to Taernyl's Folly ***"
    val helloLen = helloMessage.length
    println(helloMessage + "\n")
    for (dishData in menuList){
        val (_, name, price) = dishData.split(", ")
        val stringToPrint = name.padEnd(helloLen-price.count(), '.')
        println(stringToPrint + price)
    }
    println()

}