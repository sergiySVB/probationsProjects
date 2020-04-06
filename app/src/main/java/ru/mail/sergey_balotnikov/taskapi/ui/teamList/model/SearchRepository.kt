package ru.mail.sergey_balotnikov.taskapi.ui.teamList.model

import io.reactivex.Single


class SearchRepository(private val service: GetTeamsService){
    fun searchTeams(): Single<Result> {
        return service.search()
    }
}