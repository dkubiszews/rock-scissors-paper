package unit.utils

import models.Sign
import utils.Judge
import kotlin.test.assertEquals
import models.MatchResult
import kotlin.test.Test

class JudgeTest {

    @Test
    fun checkValidCombinations() {
        assertEquals(MatchResult.Draw, Judge.resolveMatch(Sign.Paper, Sign.Paper))
        assertEquals(MatchResult.Draw, Judge.resolveMatch(Sign.Rock, Sign.Rock))
        assertEquals(MatchResult.Draw, Judge.resolveMatch(Sign.Scissors, Sign.Scissors))

        assertEquals(MatchResult.PlayerOneWon, Judge.resolveMatch(Sign.Paper, Sign.Rock))
        assertEquals(MatchResult.PlayerOneWon, Judge.resolveMatch(Sign.Rock, Sign.Scissors))
        assertEquals(MatchResult.PlayerOneWon, Judge.resolveMatch(Sign.Scissors, Sign.Paper))

        assertEquals(MatchResult.PlayerTwoWon, Judge.resolveMatch(Sign.Paper, Sign.Scissors))
        assertEquals(MatchResult.PlayerTwoWon, Judge.resolveMatch(Sign.Rock, Sign.Paper))
        assertEquals(MatchResult.PlayerTwoWon, Judge.resolveMatch(Sign.Scissors, Sign.Rock))
    }
}