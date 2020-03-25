package ru.mail.sergey_balotnikov.taskapi.teamList

import ru.mail.sergey_balotnikov.taskapi.Router
import ru.mail.sergey_balotnikov.taskapi.teamList.model.Team

interface TeamsViewPresenterContract {
    interface ItemListView {
        fun onShowDawnloadProcess(isDownload: Boolean)
        fun onShowTeamList(teams : List<Team>)
        fun onExceptionTeamList(message : String)
    }
    interface TeamListPresenter {
        fun getTeamList()
        fun onDispose()
        fun getRouter() : Router
        fun getFilter() : Team
        fun updateFilter(filter: Team)
        fun onRefresh()
    }
}