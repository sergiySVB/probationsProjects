package ru.mail.sergey_balotnikov.taskapi.ui.teamList.model

import io.reactivex.Single

interface TeamListSupplier {
    fun getTeams(filter: Team): Single<Result>
}