package utils

import models.Sign
import models.MatchResult

/**
 * Describes match of a game.
 */
class Judge {
    companion object {
        /**
         * Resolves single match.
         *
         * @param playerOneSign Sign chosen by player one.
         * @param playerTwoSign Sign chosen by player two.
         *
         * @return Result of a match.
         */
        fun resolveMatch(playerOneSign: Sign, playerTwoSign: Sign): MatchResult = when (playerOneSign) {
            Sign.Paper -> when (playerTwoSign) {
                Sign.Rock -> MatchResult.PlayerOneWon
                Sign.Scissors -> MatchResult.PlayerTwoWon
                Sign.Paper -> MatchResult.Draw
            }

            Sign.Rock -> when (playerTwoSign) {
                Sign.Rock -> MatchResult.Draw
                Sign.Scissors -> MatchResult.PlayerOneWon
                Sign.Paper -> MatchResult.PlayerTwoWon
            }

            Sign.Scissors -> when (playerTwoSign) {
                Sign.Rock -> MatchResult.PlayerTwoWon
                Sign.Scissors -> MatchResult.Draw
                Sign.Paper -> MatchResult.PlayerOneWon
            }
        }
    }
}