package ru.mail.sergey_balotnikov.taskapi.teamDetails

import ru.mail.sergey_balotnikov.taskapi.teamDetails.model.data.Player
import ru.mail.sergey_balotnikov.taskapi.teamList.model.Team

interface TeamDetailsViewPresenterContract {
    interface TeamDetailsPresenter {
        fun getTeamDetails(team: Team, page: Int = 1)
        fun unsubscribe()
    }
    interface TeamDetailsView {
        fun onShowTeamDetails(players: List<Player>)
        fun onExceptionTeamDetails(message: String)
        fun onShowIsDawnloanProcess(isDownload: Boolean)
    }
}