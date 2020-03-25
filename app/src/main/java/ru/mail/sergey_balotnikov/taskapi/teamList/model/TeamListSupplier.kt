package ru.mail.sergey_balotnikov.taskapi.teamList.model

import io.reactivex.Single

interface TeamListSupplier {
    fun getTeams(filter: Team): Single<Result>
}