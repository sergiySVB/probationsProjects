package ru.mail.sergey_balotnikov.taskapi.ui.teamDetails.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_team_details.view.*
import ru.mail.sergey_balotnikov.taskapi.ui.Router
import ru.mail.sergey_balotnikov.taskapi.ui.teamDetails.TeamDetailsViewPresenterContract
import ru.mail.sergey_balotnikov.taskapi.ui.teamDetails.TeamDetailsViewPresenterContract.TeamDetailsPresenter
import ru.mail.sergey_balotnikov.taskapi.ui.teamDetails.model.data.Player
import ru.mail.sergey_balotnikov.taskapi.ui.teamDetails.presenter.TeamDetailsPresenterImpl
import ru.mail.sergey_balotnikov.taskapi.ui.teamList.model.Team
import ru.mail.sergey_balotnikov.taskapi.util.Constants
import ru.mail.sergey_balotnikov.taskapi.R.*


class FragmentTeamDetails: Fragment(), TeamDetailsViewPresenterContract.TeamDetailsView {
    companion object{
        fun newInstance() =
            FragmentTeamDetails()
    }

    private lateinit var presenter: TeamDetailsPresenter
    private lateinit var router: Router
    private lateinit var team: Team
    private lateinit var recycler: RecyclerView
    private lateinit var adapterPlayer: AdapterPlayers
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var errorMessage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter =
            TeamDetailsPresenterImpl(this)
        if(activity is Router){
            router = activity as Router
        }
        team = router.getTeam()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layout.fragment_team_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = view.rvPlayers
        errorMessage = view.tvErrorMessage
        swipeRefresh = view.srlSwipeRefresh
        swipeRefresh.setOnRefreshListener { presenter.getTeamDetails(team) }
        view.tvTeamAbbreviation.text = team.teamAbbreviation
        view.tvTeamCity.text = team.teamCity
        view.tvTeamConference.text = team.teamConference
        view.tvTeamDivision.text = team.teamDivision
        view.tvTeamName.text = team.teamName
        Picasso.get().load(String.format(Constants.TEAM_LOGO_PATH, team.teamAbbreviation.toLowerCase())).into(view.ivTeamLogo)
        adapterInit()
    }

    override fun onStart() {
        super.onStart()
        presenter.getTeamDetails(team)
    }

    override fun onStop() {
        presenter.unsubscribe()
        super.onStop()
    }

    override fun onShowTeamDetails(players: List<Player>) {
        adapterPlayer.addPlayersToList(players)
    }

    override fun onExceptionTeamDetails(message: String) {
        Toast.makeText(this.context, message, Toast.LENGTH_LONG).show()
    }
    private fun adapterInit(){
        adapterPlayer = AdapterPlayers()
        recycler.adapter = adapterPlayer
        recycler.layoutManager = LinearLayoutManager(this.context)

    }

    override fun onShowIsDawnloanProcess(isDownload: Boolean) {
        swipeRefresh.isRefreshing = isDownload
    }
}