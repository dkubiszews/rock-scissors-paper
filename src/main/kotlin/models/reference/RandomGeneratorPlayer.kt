package models.reference

import models.Player
import models.Sign
import kotlin.random.Random

class RandomGeneratorPlayer(override val name: String) : Player {
    private val random = Random
    private val signs = Sign.values()
    override fun chooseSign(): Sign {
        return signs[random.nextInt(signs.size)]
    }
}