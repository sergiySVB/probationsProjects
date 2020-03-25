package ru.mail.sergey_balotnikov.nbateam.model

import io.reactivex.Single
import ru.mail.sergey_balotnikov.nbateam.model.services.GetPlayersService
import ru.mail.sergey_balotnikov.taskapi.teamDetails.model.data.ResultPlayers

class SearchPlayersRepository(private val service: GetPlayersService) {
    fun searchPlayers(page: Int = 1): Single<ResultPlayers> = service.search(page = page)
}