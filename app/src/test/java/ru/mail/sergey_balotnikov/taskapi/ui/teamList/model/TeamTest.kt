package ru.mail.sergey_balotnikov.taskapi.ui.teamList.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.AfterEach


class TeamTest {

    private lateinit var team: Team

    private lateinit var validFilter: Team

    private lateinit var inValidFilter: Team


    @BeforeEach
    fun initTeams(){
        team = Team(1, "ATL", "Atlanta", "east",
            "southeast", "Atlanta Hawks", "Hawks")
        validFilter = Team(null, "atl", "","",
            "", "", "")
        inValidFilter = Team(null, "atl", "","west",
            "", "", "")
    }

    @Test
    fun filterMatcherTest() {
        assertEquals(true, team.filterMatcher(validFilter))
        assertEquals(false, team.filterMatcher(inValidFilter))
    }

    @Test
    fun getTeamIdTest() {
        assertEquals(1, team.teamId)
        assertEquals(null, validFilter.teamId)
    }

    @Test
    fun getTeamAbbreviation() {
        assertEquals("ATL", team.teamAbbreviation)
        assertEquals("ATL", inValidFilter.teamAbbreviation.toUpperCase())
    }

    @Test
    fun getTeamCity() {
        assertEquals("atlanta", team.teamCity.toLowerCase())
        assertEquals("", inValidFilter.teamCity)
    }

    @Test
    fun getTeamConference() {
        assertEquals("east", team.teamConference.toLowerCase())
    }

    @Test
    fun getTeamDivision() {
        assertEquals("southeast", team.teamDivision.toLowerCase())
    }

    @Test
    fun getFullTeamName() {
        assertEquals("atlanta hawks", team.fullTeamName.toLowerCase())
    }

    @Test
    fun getTeamName() {
        assertEquals("hawks", team.teamName.toLowerCase())
    }

    @AfterEach
    fun dropValues(){
    }
}