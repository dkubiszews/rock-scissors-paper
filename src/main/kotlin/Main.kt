import controllers.TurnController
import input.reference.ConsoleSignInput
import controllers.RetryPlayer
import models.reference.RandomGeneratorPlayer
import views.reference.ConsoleTurnView

fun main(args: Array<String>) {

    val turnView = ConsoleTurnView()
    val consoleSignInput = ConsoleSignInput()

    val player1 = RetryPlayer("player1", consoleSignInput, turnView)
    val player2 = RandomGeneratorPlayer("player2")

    val turnController = TurnController(turnView, player1, player2)

    turnController.runTurn()
}