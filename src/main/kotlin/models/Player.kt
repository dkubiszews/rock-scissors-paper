package models

interface Player {
    /**
     * Name of player.
     */
    val name: String

    /**
     * Request to choose single sign from player.
     */
    fun chooseSign(): Sign
}