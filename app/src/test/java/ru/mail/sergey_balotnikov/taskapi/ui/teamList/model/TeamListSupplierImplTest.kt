package ru.mail.sergey_balotnikov.taskapi.ui.teamList.model

import io.reactivex.Single
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Assertions.*

class TeamListSupplierImplTest {

    private lateinit var supplier:TeamListSupplierImpl
    private lateinit var inputList: List<Team>
    private lateinit var filter: Team
    private lateinit var resultList: List<Team>
    private lateinit var service: GetTeamsService

    @BeforeEach
    fun setUp(){
        supplier = TeamListSupplierImpl()
        inputList = listOf(Team(1, "ABC", "", "east", "", "", ""),
            Team(1, "ABs", "112", "east", "123", "", ""),
            Team(1, "ABC", "", "east", "", "", "qweryt"),
            Team(1, "ABC", "", "east", "", "tre", "")
        )
        filter = Team(1, "ABC", "","","","","")
        resultList = listOf(Team(1, "ABC", "", "east", "", "", ""),
            Team(1, "ABC", "", "east", "", "", "qweryt"),
            Team(1, "ABC", "", "east", "", "tre", "")
        )
        service = GetTeamsService.create()
    }



    @Test
    fun `filter Test`(){
        assertEquals(resultList, supplier.filter(inputList, filter))
    }

    /*@Test
    fun `is service return single`(){
        assertTrue(supplier.getTeams(filter) is Single<Result>)
    }*/
}