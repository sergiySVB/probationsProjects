package ru.mail.sergey_balotnikov.taskapi.ui.teamDetails.presenter

import io.reactivex.disposables.Disposable
import ru.mail.sergey_balotnikov.taskapi.ui.teamDetails.TeamDetailsViewPresenterContract.*
import ru.mail.sergey_balotnikov.taskapi.ui.teamDetails.model.TeamDetailsSupplierImpl
import ru.mail.sergey_balotnikov.taskapi.ui.teamList.model.Team
import java.io.IOException

class TeamDetailsPresenterImpl(private val view: TeamDetailsView) : TeamDetailsPresenter {


    private var teamsRequestSubscription: Disposable? = null
    //private var metaRequestSubscription: Disposable? = null


    override fun getTeamDetails(team: Team, page: Int) {
        view.onShowIsDawnloanProcess(true)
        val supplier =
            TeamDetailsSupplierImpl()
        val result = supplier.getPlayers(team, page)

        teamsRequestSubscription?.dispose()
        teamsRequestSubscription = result.subscribe({ players ->
            view.onShowTeamDetails(players)
            view.onShowIsDawnloanProcess(false)
        }, { error ->
            view.onShowTeamDetails(supplier.getAvailablePlayers())
            view.onShowIsDawnloanProcess(false)
            handleError(error)
        })
        /*metaRequestSubscription?.dispose()
        metaRequestSubscription =
            result.map { it.meta }.subscribe({ meta ->
                if (meta.next_page != null) {
                    getTeamDetails(team, meta.next_page)
                    Log.d(Constants.LOG_TAG, "Meta" + meta.next_page + " " + meta.current_page)
                } else {
                    Log.d(Constants.LOG_TAG, "last page")
                }
            }, {})*/
    }

    override fun unsubscribe() {
        teamsRequestSubscription?.dispose()
    }

    private fun handleError(e: Throwable) {
        val message = when (e) {
            is IOException -> "Bad connection"
            else -> "Unknown error"
        }
        view.onExceptionTeamDetails(message)
    }
}