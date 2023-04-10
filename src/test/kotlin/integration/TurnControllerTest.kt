package integration

import controllers.RetryPlayer
import controllers.TurnController
import input.SignInput
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifySequence
import models.Player
import models.Sign
import views.PlayerView
import views.TurnView
import kotlin.test.Test
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

class TurnControllerTest {

    companion object {
        private const val PLAYER_ONE_NAME = "TestPlayerOne"
        private const val PLAYER_TWO_NAME = "TestPlayerTwo"
    }

    private val turnViewMock = mockk<TurnView>(relaxed = true)
    private val playerOneViewMock = mockk<PlayerView>(relaxed = true)
    private val playerTwoViewMock = mockk<PlayerView>(relaxed = true)
    private val signInputOneMock = mockk<SignInput>(relaxed = true)
    private val signInputTwoMock = mockk<SignInput>(relaxed = true)
    private val playerOne = RetryPlayer(PLAYER_ONE_NAME, signInputOneMock, playerOneViewMock)
    private val playerTwo = RetryPlayer(PLAYER_TWO_NAME, signInputTwoMock, playerTwoViewMock)
    private lateinit var sut: TurnController;

    @BeforeTest
    fun setUp() {
        every { turnViewMock.showTitle() } returns Unit
        every { turnViewMock.showChosenSigns(any(), any(), any(), any()) } returns Unit
        sut = TurnController(turnViewMock, playerOne, playerTwo)
    }

    @Test
    fun oneTurnTest() {
        every { turnViewMock.showWinner(any()) } returns Unit
        every { signInputOneMock.requestSign() } returns Sign.Scissors
        every { signInputTwoMock.requestSign() } returns Sign.Paper
        every { playerOneViewMock.askToProvideInput(any())} returns Unit
        every { playerTwoViewMock.askToProvideInput(any())} returns Unit

        sut.runTurn()

        verify(exactly = 1) {
            turnViewMock.showChosenSigns(
                PLAYER_ONE_NAME, "Scissors", PLAYER_TWO_NAME, "Paper")
        }

        verify(exactly = 1) {
            turnViewMock.showWinner(PLAYER_ONE_NAME)
        }
    }

    @Test
    fun multipleTurnsTest() {
        every { turnViewMock.showWinner(any()) } returns Unit
        every { signInputOneMock.requestSign() } returnsMany listOf(Sign.Scissors, Sign.Paper, Sign.Scissors)
        every { signInputTwoMock.requestSign() } returnsMany listOf(Sign.Paper, Sign.Paper, Sign.Rock)
        every { playerOneViewMock.askToProvideInput(any())} returns Unit
        every { playerTwoViewMock.askToProvideInput(any())} returns Unit

        sut.runTurn()
        sut.runTurn()
        sut.runTurn()

        verifySequence {
            turnViewMock.showTitle()
            turnViewMock.showChosenSigns(
                PLAYER_ONE_NAME, "Scissors", PLAYER_TWO_NAME, "Paper")
            turnViewMock.showWinner(PLAYER_ONE_NAME)
            turnViewMock.showTitle()
            turnViewMock.showChosenSigns(
                PLAYER_ONE_NAME, "Paper", PLAYER_TWO_NAME, "Paper")
            turnViewMock.showDraw()
            turnViewMock.showTitle()
            turnViewMock.showChosenSigns(
                PLAYER_ONE_NAME, "Scissors", PLAYER_TWO_NAME, "Rock")
            turnViewMock.showWinner(PLAYER_TWO_NAME)
        }
    }

    @Test
    fun retryInputTest() {
        every { signInputOneMock.requestSign() } returnsMany listOf(null, Sign.Scissors)
        every { signInputTwoMock.requestSign() } returns Sign.Paper

        sut.runTurn()

        verify(exactly = 2) {
            signInputOneMock.requestSign()
        }
    }


}