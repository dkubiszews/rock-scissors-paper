package views

/**
 * Interface to represent player's view.
 */
interface PlayerView {

    /**
     * Show information to user that Sing as input is required.
     * 
     * @param playerName Name of the player that is requested.
     */
    fun askToProvideInput(playerName: String)
}