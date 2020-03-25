package ru.mail.sergey_balotnikov.taskapi.teamDetails.model

import io.reactivex.Observable
import io.reactivex.Single
import ru.mail.sergey_balotnikov.taskapi.teamDetails.model.data.Player
import ru.mail.sergey_balotnikov.taskapi.teamDetails.model.data.ResultPlayers
import ru.mail.sergey_balotnikov.taskapi.teamList.model.Team

interface TeamDetailsSupplier {
    fun getPlayers(team: Team, page: Int = 1): Single<ArrayList<Player>>
}