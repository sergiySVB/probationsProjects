package ru.mail.sergey_balotnikov.taskapi.ui.teamDetails.model

import io.reactivex.Single
import ru.mail.sergey_balotnikov.taskapi.ui.teamDetails.model.data.Player
import ru.mail.sergey_balotnikov.taskapi.ui.teamList.model.Team

interface TeamDetailsSupplier {
    fun getPlayers(team: Team, page: Int = 1): Single<ArrayList<Player>>
}