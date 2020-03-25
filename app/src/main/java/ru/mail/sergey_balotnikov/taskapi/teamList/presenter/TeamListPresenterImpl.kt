package ru.mail.sergey_balotnikov.taskapi.teamList.presenter

import android.widget.TextView
import io.reactivex.disposables.Disposable
import ru.mail.sergey_balotnikov.taskapi.Router
import ru.mail.sergey_balotnikov.taskapi.teamList.TeamsViewPresenterContract
import ru.mail.sergey_balotnikov.taskapi.teamList.TeamsViewPresenterContract.ItemListView
import ru.mail.sergey_balotnikov.taskapi.teamList.model.TeamListSupplierImpl
import ru.mail.sergey_balotnikov.taskapi.teamList.model.Team
import java.io.IOException

class TeamListPresenterImpl: TeamsViewPresenterContract.TeamListPresenter {

    private val view: ItemListView
    private val supplier:TeamListSupplierImpl
    private val router: Router
    private var teamsRequestSubscription: Disposable? = null


    constructor(view: ItemListView, router: Router) {
        this.view = view
        supplier = TeamListSupplierImpl()
        this.router = router
    }


    override fun getTeamList() {
        view.onShowDawnloadProcess(true)
        val result = supplier.getTeams(getFilter())
        this.teamsRequestSubscription?.dispose()

        this.teamsRequestSubscription = result.map { it.teams }?.subscribe({
                teams ->
            view.onShowTeamList(teams)
            view.onShowDawnloadProcess(false)
            }, {
                error-> this.handleError(error)
            })
    }

    private fun handleError(e: Throwable) {
        val message = when (e) {
            is IOException -> "Bad connection"
            else -> "Unknown error"
        }
        view.onExceptionTeamList(message)
        view.onShowDawnloadProcess(false)
    }

    override fun onDispose() {
        this.teamsRequestSubscription?.dispose()
    }

    override fun onRefresh(){

        getTeamList()
    }
    override fun getFilter()= router.getFilter()

    override fun updateFilter(filter: Team){
        router.updateFilter(filter)
        getTeamList()
    }

    override fun getRouter()=router
}