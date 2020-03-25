package ru.mail.sergey_balotnikov.taskapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ru.mail.sergey_balotnikov.taskapi.teamDetails.view.FragmentTeamDetails
import ru.mail.sergey_balotnikov.taskapi.teamList.view.FragmentTeamList
import ru.mail.sergey_balotnikov.taskapi.teamFilter.view.FragmentTeamFilter
import ru.mail.sergey_balotnikov.taskapi.teamList.model.Team
import ru.mail.sergey_balotnikov.taskapi.util.Constants

class MainActivity : AppCompatActivity(), Router {

    private var activeFragmentName: String = Constants.TAG_TEEM_LIST_SCREEN
    private var filter = Team(null, "", "",
        "", "", "", "")
    private var team = Team(null, "", "",
        "", "", "", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showTeamList()
    }

    override fun onResume() {
        super.onResume()
        Log.d(Constants.LOG_TAG, "main on resume")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable(Constants.KEY_FILTER, filter)
        outState.putSerializable(Constants.KEY_TEAM, team)
        outState.putString(Constants.KEY_FRAGMENT, activeFragmentName)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        filter = savedInstanceState.getSerializable(Constants.KEY_FILTER) as Team
        team = savedInstanceState.getSerializable(Constants.KEY_TEAM) as Team

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
        filterArgs.putSerializable(Constants.KEY_FILTER, filter)
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

    override fun getTeam() = team

    override fun setTeam(team: Team) {
        this.team = team
        showTeamDetails()
    }

    override fun getFilter(): Team {
        return filter
    }

    override fun updateFilter(filter: Team) {
        this.filter =filter
        showTeamList()
    }

    override fun dropFilter() {
        filter = Team(null, "","","", "",
            "","")
    }
}
