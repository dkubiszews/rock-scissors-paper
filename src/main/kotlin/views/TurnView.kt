package views

/**
 * Interface to represent one turn of the game.
 */
interface TurnView {

    /**
     * Show title on beginning of every game.
     */
    fun showTitle()

    /**
     * Show information about winner.
     *
     * @param playerName Name for player that won one turn.
     */
    fun showWinner(playerName: String)

    /**
     * Show information about game that resulted in draw.
     */
    fun showDraw()

    /**
     * Show information about chosen signs within one turn.
     *
     * @param playerOneName Name of player one.
     * @param playerOneSign Sign chosen by player one.
     * @param playerTwoName Name of player two.
     * @param playerTwoSign Sign chosen by player two.
     */
    fun showChosenSigns(
        playerOneName: String, playerOneSign: String, playerTwoName: String, playerTwoSign: String)
}