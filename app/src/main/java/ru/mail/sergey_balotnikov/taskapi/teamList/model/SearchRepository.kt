package ru.mail.sergey_balotnikov.taskapi.teamList.model

import io.reactivex.Single


class SearchRepository(val service: GetTeamsService){
    fun searchTeams(): Single<Result> {
        return service.search()
    }
}