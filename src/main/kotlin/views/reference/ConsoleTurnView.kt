package views.reference

import views.TurnView
import views.PlayerView

class ConsoleTurnView : TurnView, PlayerView {
    override fun showTitle() {
        println("New round of Rock, Scissors, Paper")
    }

    override fun showWinner(playerName: String) {
        println("Player $playerName won!")
    }

    override fun showDraw() {
        println("Draw!")
    }

    override fun askToProvideInput(playerName: String) {
        println("Provide 'r' for Rock, 's' forScissors or 'p' for Paper")
    }

    override fun showChosenSigns(
        playerOneName: String, playerOneSign: String, playerTwoName: String, playerTwoSign: String) {
        println("Player $playerOneName choose $playerOneSign and player $playerTwoName choose $playerTwoSign")
    }
}
