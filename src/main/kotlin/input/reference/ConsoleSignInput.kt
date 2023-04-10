package input.reference

import input.SignInput
import models.Sign

class ConsoleSignInput: SignInput {
    override fun requestSign(): Sign? = when (readlnOrNull()) {
        "r" -> Sign.Rock
        "s" -> Sign.Scissors
        "p" -> Sign.Paper
        else -> null
    }
}