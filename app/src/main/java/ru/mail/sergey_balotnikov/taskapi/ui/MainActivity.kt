package ru.mail.sergey_balotnikov.taskapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ru.mail.sergey_balotnikov.taskapi.MainActivityRouter
import ru.mail.sergey_balotnikov.taskapi.NBAApp
import ru.mail.sergey_balotnikov.taskapi.R
import ru.mail.sergey_balotnikov.taskapi.di.component.ActivityComponent
import ru.mail.sergey_balotnikov.taskapi.di.component.DaggerActivityComponent
import ru.mail.sergey_balotnikov.taskapi.di.module.ActivityModule
import ru.mail.sergey_balotnikov.taskapi.ui.teamDetails.view.FragmentTeamDetails
import ru.mail.sergey_balotnikov.taskapi.ui.teamList.view.FragmentTeamList
import ru.mail.sergey_balotnikov.taskapi.ui.teamFilter.view.FragmentTeamFilter
import ru.mail.sergey_balotnikov.taskapi.ui.teamList.model.Team
import ru.mail.sergey_balotnikov.taskapi.util.Constants
import javax.inject.Inject
import javax.inject.Named

class MainActivity : AppCompatActivity(), Router {

    companion object{
        lateinit var activityComponent: ActivityComponent
        fun getComponent() = activityComponent
    }
    @Inject
    lateinit var router: MainActivityRouter

    private var activeFragmentName: String = Constants.TAG_TEEM_LIST_SCREEN

    @Inject
    @Named("1")
    lateinit var filter2: Team
    @Inject
    @Named("2")
    lateinit var team2: Team
    /*private var filter = Team(
        null, "", "",
        "", "", "", ""
    )
    private var team = Team(
        null, "", "",
        "", "", "", ""
    )*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.setupComponent()
        filter2 = activityComponent.provideFilter()
        team2 = activityComponent.provideSelectedTeam()

        showTeamList()

        router.goToDetails()
    }

    private fun setupComponent() {

        activityComponent = DaggerActivityComponent.builder()
            .appComponent(NBAApp.appComponent())
            .activityModule(ActivityModule(this))
            .build()

        activityComponent.inject(this)
    }

    override fun onResume() {
        super.onResume()
        Log.d(Constants.LOG_TAG, "main on resume")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable(Constants.KEY_FILTER, filter2)
        outState.putSerializable(Constants.KEY_TEAM, team2)
        outState.putString(Constants.KEY_FRAGMENT, activeFragmentName)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        filter2 = savedInstanceState.getSerializable(Constants.KEY_FILTER) as Team
        team2 = savedInstanceState.getSerializable(Constants.KEY_TEAM) as Team

        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun showTeamList() {
        val fragmentTeamList = FragmentTeamList.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragmentTeamList, FragmentTeamList::class.java.simpleName)
            .commit()
    }

    override fun showTeamDetails() {
        val fragmentTeamDetails = FragmentTeamDetails.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragmentTeamDetails, FragmentTeamList::class.java.simpleName)
            .addToBackStack("2")
            .commit()
    }


    override fun showFilterScreen() {
        val filterArgs = Bundle()
        filterArgs.putSerializable(Constants.KEY_FILTER, filter2)
        val fragmentFilter = FragmentTeamFilter.newInstance()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, fragmentFilter, FragmentTeamList::class.java.simpleName)
            .addToBackStack("3")
            .commit()
    }

    override fun onPause() {
        Log.d(Constants.LOG_TAG, "main on pause")
        super.onPause()
    }

    override fun getTeam() = team2

    override fun setTeam(team: Team) {
        this.team2 = team
        showTeamDetails()
    }

    override fun getFilter(): Team {
        return filter2
    }

    override fun updateFilter(filter: Team) {
        this.filter2 =filter
        showTeamList()
    }

    override fun dropFilter() {
        filter2 = Team(
            null, "", "", "", "",
            "", ""
        )
    }
}
