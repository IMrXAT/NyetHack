package com.bignerdranch.nyethack


fun main(args: Array<String>) {

    val player = Player("Dima", /*75, true, false, "gnome"*/)
    player.castFireball(45)

    // player state
    printPlayerStatus(player)

}

private fun printPlayerStatus(player: Player) {

    println("(Aura: ${player.auraColor()}) " +
            "(Blessed: ${if (player.isBlessed) "YES" else "NO"})")
    println("${player.name} ${player.formatHealthStatus()}")
    println("${player.name} is ${player.race}")
}







