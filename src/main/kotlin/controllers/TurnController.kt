package controllers

import models.MatchResult
import models.Player
import utils.Judge
import views.TurnView

/**
 * Manages turns of the RockScissorsPaper game.
 */
class TurnController(
    private val turnView: TurnView,
    private val playerOne: Player,
    private val playerTwo: Player) {

    /**
     * Run one turn of the game.
     * @note Multiple turns can be run on one object of `TurnController`.
     */
    fun runTurn() {
        turnView.showTitle()

        val playerOneSign = playerOne.chooseSign()
        val playerTwoSign = playerTwo.chooseSign()

        turnView.showChosenSigns(
            playerOne.name,
            playerOneSign.toString(),
            playerTwo.name,
            playerTwoSign.toString())

        when (Judge.resolveMatch(playerOneSign, playerTwoSign)) {
            MatchResult.Draw -> turnView.showDraw()
            MatchResult.PlayerOneWon -> turnView.showWinner(playerOne.name)
            MatchResult.PlayerTwoWon -> turnView.showWinner(playerTwo.name)
        }
    }
}