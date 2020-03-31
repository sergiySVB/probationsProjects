package ru.mail.sergey_balotnikov.taskapi.ui.teamDetails.model

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.mail.sergey_balotnikov.nbateam.model.SearchPlayersRepository
import ru.mail.sergey_balotnikov.nbateam.model.services.GetPlayersService
import ru.mail.sergey_balotnikov.taskapi.ui.teamDetails.model.data.Player
import ru.mail.sergey_balotnikov.taskapi.ui.teamDetails.model.data.ResultPlayers
import ru.mail.sergey_balotnikov.taskapi.ui.teamList.model.Team

class TeamDetailsSupplierImpl:
    TeamDetailsSupplier {
/*
    private val service: GetPlayersService = GetPlayersService.create()
    private val repository = SearchPlayersRepository(service)

    override fun getPlayers(team: Team, page: Int): Single<ResultPlayers> {
        val service: GetPlayersService = GetPlayersService.create()
        val repository = SearchPlayersRepository(service)
        return repository.searchPlayers(page=page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { ResultPlayers(filter(it.players, team), it.meta) }
    }*/
    private var allPlayers = ArrayList<Player>()


    override fun getPlayers(team: Team, page: Int): Single<ArrayList<Player>> {
        allPlayers = ArrayList()
        return this.getAllPlayersInternal(allPlayers, team, 1)

    }

    private fun getAllPlayersInternal(currentPlayers: ArrayList<Player>, team: Team, nextPage: Int): Single<ArrayList<Player>> {
        return getForPagePlayers(team, nextPage).flatMap {
            currentPlayers.addAll(it.players)
            if (it.meta.next_page == null) {
                Single.just(currentPlayers)
            } else {
                getAllPlayersInternal(currentPlayers, team, it.meta.next_page)
            }
        }
    }

    private fun getForPagePlayers(team: Team, page: Int): Single<ResultPlayers> {
        val service: GetPlayersService = GetPlayersService.create()
        val repository = SearchPlayersRepository(service)
        return repository.searchPlayers(page=page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                ResultPlayers(
                    filter(
                        it.players,
                        team
                    ), it.meta
                )
            }
    }


    private fun filter(teams: List<Player>, team: Team): List<Player> =
        teams.filter {
            it.team.teamId == team.teamId
        }
    fun getAvailablePlayers() = allPlayers
}