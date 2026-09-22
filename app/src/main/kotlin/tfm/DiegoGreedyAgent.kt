package tfm

import games.planetwars.agents.Action
import games.planetwars.agents.PlanetWarsPlayer
import games.planetwars.core.GameState
import games.planetwars.core.Player

class DiegoGreedyAgent : PlanetWarsPlayer() {

    override fun getAction(gameState: GameState): Action {

        // 1. Buscar mis planetas disponibles
        val myPlanets = gameState.planets.filter {
            it.owner == player &&
            it.transporter == null &&
            it.nShips > 10
        }

        if (myPlanets.isEmpty()) {
            return Action.doNothing()
        }

        // 2. Elegir mi planeta con más naves
        val source = myPlanets.maxByOrNull {
            it.nShips
        } ?: return Action.doNothing()

        // 3. Buscar planetas neutrales
        val neutralPlanets = gameState.planets.filter {
            it.owner == Player.Neutral
        }

        if (neutralPlanets.isEmpty()) {
            return Action.doNothing()
        }

        // 4. Elegir el planeta neutral más débil
        val target = neutralPlanets.minByOrNull {
            it.nShips
        } ?: return Action.doNothing()

        // 5. Solo atacar si tenemos ventaja
        if (source.nShips <= target.nShips) {
            return Action.doNothing()
        }

        // 6. Enviar la mitad de nuestras naves
        val shipsToSend = source.nShips / 2

        return Action(
            player,
            source.id,
            target.id,
            shipsToSend
        )
    }

    override fun getAgentType(): String =
        "Diego Greedy Agent"
}