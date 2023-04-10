package unit.controllers

import controllers.TurnController
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import models.Player
import models.Sign
import views.TurnView
import kotlin.test.Test
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

class TurnControllerTest {

    companion object {
        private const val PLAYER_ONE_NAME = "TestPlayerOne"
        private const val PLAYER_TWO_NAME = "TestPlayerTwo"
    }

    private val turnViewMock = mockk<TurnView>()
    private val playerOneMock = mockk<Player>()
    private val playerTwoMock = mockk<Player>()
    private lateinit var sut: TurnController;

    @BeforeTest
    fun setUp() {
        every { turnViewMock.showTitle() } returns Unit
        every { turnViewMock.showChosenSigns(any(), any(), any(), any()) } returns Unit
        every { playerOneMock.name } returns PLAYER_ONE_NAME
        every { playerTwoMock.name } returns PLAYER_TWO_NAME
        sut = TurnController(turnViewMock, playerOneMock, playerTwoMock)
    }

    @AfterTest
    fun tearDown() {
        verify(exactly = 1) { playerOneMock.chooseSign() }
        verify(exactly = 1) { playerTwoMock.chooseSign() }
        verify(exactly = 1) { turnViewMock.showTitle() }
    }

    @Test
    fun playerOneWin() {
        every { turnViewMock.showWinner(any()) } returns Unit
        every { playerOneMock.chooseSign() } returns Sign.Scissors
        every { playerTwoMock.chooseSign() } returns Sign.Paper

        sut.runTurn()

        verify(exactly = 1) {
            turnViewMock.showChosenSigns(
                PLAYER_ONE_NAME, "Scissors", PLAYER_TWO_NAME, "Paper")
        }

        verify(exactly = 1) { turnViewMock.showWinner(PLAYER_ONE_NAME) }
    }

    @Test
    fun playerTwoWin() {
        every { turnViewMock.showWinner(any()) } returns Unit
        every { playerOneMock.chooseSign() } returns Sign.Scissors
        every { playerTwoMock.chooseSign() } returns Sign.Rock

        sut.runTurn()

        verify(exactly = 1) {
            turnViewMock.showChosenSigns(
                PLAYER_ONE_NAME, "Scissors", PLAYER_TWO_NAME, "Rock")
        }

        verify(exactly = 1) { turnViewMock.showWinner(PLAYER_TWO_NAME) }
    }

    @Test
    fun draw() {
        every { turnViewMock.showDraw() } returns Unit
        every { playerOneMock.chooseSign() } returns Sign.Scissors
        every { playerTwoMock.chooseSign() } returns Sign.Scissors

        sut.runTurn()

        verify(exactly = 1) {
            turnViewMock.showChosenSigns(
                PLAYER_ONE_NAME, "Scissors", PLAYER_TWO_NAME, "Scissors")
        }

        verify(exactly = 1) { turnViewMock.showDraw() }
    }
}