package ru.mail.sergey_balotnikov.taskapi.teamList.model

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class TeamListSupplierImpl: TeamListSupplier {

    private val repository = SearchRepository(GetTeamsService.create())

    override fun getTeams(filter: Team): Single<Result> {

        return repository.searchTeams()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                Result(filter(it.teams, filter))
            }
    }

    private fun filter(teams: List<Team>, filter: Team): List<Team> =
        teams.filter { it.filterMatcher(filter)
        }

}