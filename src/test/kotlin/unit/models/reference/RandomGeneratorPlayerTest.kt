package unit.models.reference

import models.Sign
import models.reference.RandomGeneratorPlayer
import kotlin.test.Test
import kotlin.test.assertTrue

class RandomGeneratorPlayerTest {
    @Test
    fun validResult() {
        val sut = RandomGeneratorPlayer("TestPlayer")

        val sign = sut.chooseSign()

        assertTrue(Sign.values().toSet().contains(sign))
    }
}