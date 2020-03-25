package ru.mail.sergey_balotnikov.taskapi.teamList.view

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.appcompat.widget.SearchView.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.fragment_team_list.view.*
import ru.mail.sergey_balotnikov.taskapi.Router
import ru.mail.sergey_balotnikov.taskapi.teamList.TeamsViewPresenterContract
import ru.mail.sergey_balotnikov.taskapi.teamList.TeamsViewPresenterContract.TeamListPresenter
import ru.mail.sergey_balotnikov.taskapi.teamList.model.Team
import ru.mail.sergey_balotnikov.taskapi.teamList.presenter.TeamListPresenterImpl
import androidx.appcompat.widget.SearchView.OnQueryTextListener as OnQueryTextListener
import android.os.Handler




class FragmentTeamList:Fragment(), TeamsViewPresenterContract.ItemListView, OnTeamClickListener {
    companion object {
        fun newInstance(): FragmentTeamList {
            return FragmentTeamList()
        }
    }

    private lateinit var presenter: TeamListPresenter
    private val adapter: TeamListAdapter = TeamListAdapter()
    private var search: SearchView? = null
    private var filterImageButton: ImageButton? = null
    private var swipeRefreshLayout: SwipeRefreshLayout? = null
    private lateinit var recycler: RecyclerView
    private lateinit var router: Router
    private lateinit var emptyListMessage: TextView
    private val handler = Handler()
    private lateinit var throttlingRun: Runnable


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (activity is Router) {
            router = activity as Router
        }
        presenter = TeamListPresenterImpl(this, router)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(ru.mail.sergey_balotnikov.taskapi.R.layout.fragment_team_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefreshLayout = view.srlSwipeRefresh
        emptyListMessage = view.tvEmptyList
        swipeRefreshLayout?.setOnRefreshListener {
            if(emptyListMessage.visibility == View.VISIBLE){
                router.dropFilter()
            }
            presenter.onRefresh()
        }
        recycler = view.rvItemList
        filterImageButton = view.ibFilter
        search = view.svSearch
        recyclerInit()
        searchListenerInit()
        filterImageButton?.setOnClickListener {
            router.showFilterScreen()
        }
    }


    override fun onResume() {
        super.onResume()
        presenter.getTeamList()
    }

    override fun onTeamClick(team: Team) {
        router.setTeam(team)
    }

    override fun onShowTeamList(teams: List<Team>) {
        if(teams.isEmpty()){
            emptyListMessage.visibility = View.VISIBLE
        } else {emptyListMessage.visibility = View.GONE}
        adapter.setTeamList(teams)
    }

    override fun onExceptionTeamList(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        adapter.setTeamList(listOf())
        //recyclerInit()
    }

    private fun recyclerInit() {
        adapter.setOnTeamClickListener(this as OnTeamClickListener)
        recycler.adapter = adapter
        if(activity?.resources?.configuration?.orientation == Configuration.ORIENTATION_PORTRAIT){
            recycler.layoutManager = LinearLayoutManager(this.context)
        } else {
            recycler.layoutManager = GridLayoutManager(this.context, 2)
        }
    }


    override fun onShowDawnloadProcess(isDownload: Boolean) {
        swipeRefreshLayout?.isRefreshing = isDownload
    }

    private fun searchListenerInit() {
        search?.imeOptions = EditorInfo.IME_ACTION_DONE
        search?.setOnClickListener{ search?.setIconifiedByDefault(false)
                search?.isIconified = false
        }
        search?.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                throttlingRun = Runnable {
                    val filter = presenter.getFilter()
                    filter.fullTeamName = newText
                    presenter.updateFilter(filter)
                }
                handler.removeCallbacks(throttlingRun)
                handler.postDelayed(throttlingRun, 1000)

                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
        })
    }
}