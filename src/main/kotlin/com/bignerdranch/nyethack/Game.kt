package com.bignerdranch.nyethack

import java.lang.Exception
import java.util.*


fun main(args: Array<String>) {
    Game.play()
}

object Game{
    private val player = Player("Dima")
    private var currentRoom: Room = TownSquare()

    private var worldMap = listOf(
        listOf(currentRoom, Room("Tavern"), Room("Back Room")),
        listOf(Room("Long Corridor"), Room("Generic Room")))

    private fun move(directionInput: String) =
        try {
            val direction = Direction.valueOf(directionInput.uppercase(Locale.getDefault()))
            val newPosition = direction.updateCoordinate(player.currentPosition)
            if (!newPosition.isInBounds){
                throw IllegalStateException("$direction is out of bounds")
            }
            val newRoom = worldMap[newPosition.y][newPosition.x]
            player.currentPosition = newPosition
            currentRoom = newRoom
            "You move $direction to the ${newRoom.name}.\n${newRoom.load()}"
        }
        catch (e: Exception){
            "Invalid direction: $directionInput"
        }
    init{
        println("Welcome, adventurer")
        player.castFireball(45)
    }


    fun play(){
        while (true){
            println(currentRoom.description())
            println(currentRoom.load())

            // player state
            printPlayerStatus(player)

            print("> Enter your command: ")
            println(GameInput(readlnOrNull()).processCommand())

        }
    }

    private class GameInput(arg: String?){
        private val input = arg ?: ""
        val command = input.split(" ")[0]
        val argument = input.split(" ").getOrElse(1) { "" }

        private fun commandNotFound() = "You are shit, enter smth else"

        fun processCommand() = when(command.lowercase(Locale.getDefault())){
            "move" -> move(argument)
            else -> commandNotFound()

        }

    }

}

private fun printPlayerStatus(player: Player) {

    println("(Aura: ${player.auraColor()}) " +
            "(Blessed: ${if (player.isBlessed) "YES" else "NO"})")
    println("${player.name} ${player.formatHealthStatus()}")
    println("${player.name} is ${player.race}")
}






