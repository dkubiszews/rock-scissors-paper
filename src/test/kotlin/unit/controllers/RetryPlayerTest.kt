package unit.controllers

import input.SignInput
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import models.Sign
import controllers.RetryPlayer
import views.PlayerView
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class RetryPlayerTest {

    private val playerViewMock = mockk<PlayerView>()
    private val signInputMock = mockk<SignInput>()
    private val testPlayerName = "testPlayer"
    private lateinit var sut: RetryPlayer;

    @BeforeTest
    fun setUp() {
        every { playerViewMock.askToProvideInput(any()) } returns Unit
        sut = RetryPlayer(testPlayerName, signInputMock, playerViewMock)
    }

    @Test
    fun checkRock() {
        every { signInputMock.requestSign() } returns Sign.Rock
        assertEquals(Sign.Rock, sut.chooseSign())
        verify(exactly = 1) { signInputMock.requestSign() }
        verify(exactly = 1) { playerViewMock.askToProvideInput(testPlayerName) }
    }

    @Test
    fun checkScissors() {
        every { signInputMock.requestSign() } returns Sign.Scissors
        assertEquals(Sign.Scissors, sut.chooseSign())
        verify(exactly = 1) { signInputMock.requestSign() }
        verify(exactly = 1) { playerViewMock.askToProvideInput(testPlayerName) }
    }

    @Test
    fun checkPaper() {
        every { signInputMock.requestSign() } returns Sign.Paper
        assertEquals(Sign.Paper, sut.chooseSign())
        verify(exactly = 1) { signInputMock.requestSign() }
        verify(exactly = 1) { playerViewMock.askToProvideInput(testPlayerName) }
    }

    @Test
    fun nullSignCauseRetry() {
        every { signInputMock.requestSign() } returnsMany listOf(null, Sign.Paper)
        assertEquals(Sign.Paper, sut.chooseSign())
        verify(exactly = 2) { signInputMock.requestSign() }
        verify(exactly = 2) { playerViewMock.askToProvideInput(testPlayerName) }
    }
}