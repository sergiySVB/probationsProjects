package ru.mail.sergey_balotnikov.taskapi.ui

import ru.mail.sergey_balotnikov.taskapi.ui.teamList.model.Team

interface  Router{
    fun updateFilter(filter: Team)
    fun dropFilter()
    fun getFilter(): Team
    fun getTeam(): Team
    fun setTeam(team: Team)
    fun showTeamDetails()
    fun showTeamList()
    fun showFilterScreen()
}