package tfm

import competition_entry.GreedyHeuristicAgent
import games.planetwars.core.GameParams
import games.planetwars.core.Player
import games.planetwars.runners.GameRunner

fun main() {
    val gameParams = GameParams(numPlanets = 20)

    val agent1 = DiegoGreedyAgent()
    val agent2 = GreedyHeuristicAgent()

    val gameRunner = GameRunner(
        agent1,
        agent2,
        gameParams
    )

    val nGames = 1000

    val results = gameRunner.runGames(nGames)

    val diegoWins = results[Player.Player1] ?: 0
    val greedyWins = results[Player.Player2] ?: 0
    val draws = results[Player.Neutral] ?: 0

    println("Results after $nGames games:")
    println("DiegoGreedyAgent wins: $diegoWins")
    println("GreedyHeuristicAgent wins: $greedyWins")
    println("Draws: $draws")

    println()
    println("Diego win rate: ${diegoWins * 100.0 / nGames}%")
    println("Greedy win rate: ${greedyWins * 100.0 / nGames}%")
}