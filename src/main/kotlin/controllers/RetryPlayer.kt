package controllers

import input.SignInput
import models.Player
import models.Sign
import views.PlayerView

/**
 * Player that retry when the requested sign is invalid.
 */
class RetryPlayer(
    override val name: String,
    private val signInput: SignInput,
    private val playerView: PlayerView
) : Player {

    /**
     * Choose one sign.
     * @return `Sign`.
     * @note retries until valid sign is acquired.
     */
    override fun chooseSign(): Sign {
        var result: Sign?
        do {
            playerView.askToProvideInput(name)
            result = signInput.requestSign()
        } while (result == null)
        return result
    }

}
