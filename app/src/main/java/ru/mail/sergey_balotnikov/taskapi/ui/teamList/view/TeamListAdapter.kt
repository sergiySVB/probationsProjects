package ru.mail.sergey_balotnikov.taskapi.ui.teamList.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_team_list.view.*
import ru.mail.sergey_balotnikov.taskapi.R
import ru.mail.sergey_balotnikov.taskapi.ui.teamList.model.Team
import ru.mail.sergey_balotnikov.taskapi.util.Constants

class TeamListAdapter : RecyclerView.Adapter<TeamListAdapter.ItemHolder>() {
    private var teamList:List<Team>
    private lateinit var listener: OnTeamClickListener
    init{
        teamList= emptyList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_team_list, parent, false)
        return ItemHolder(view)
    }

    override fun getItemCount() : Int {
        return teamList.size
    }
    fun setTeamList(teamList: List<Team>){
        this.teamList = teamList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(teamList?.get(position))
        holder.itemView.setOnClickListener{
            listener.onTeamClick(teamList[position])
        }
    }
    fun setOnTeamClickListener(listener: OnTeamClickListener){
        this.listener = listener
    }

    class ItemHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        private var fullTeamName:TextView? = null
        private var teamDivision:TextView? = null
        private var teamConference:TextView? = null
        private var teamLogo:ImageView? = null

        init{
            fullTeamName=itemView.tvTeamName
            teamDivision=itemView.tvTeamDivision
            teamConference=itemView.tvTeamConference
            teamLogo=itemView.ivLogo
        }

        fun bind(team : Team?){
            fullTeamName?.text = team?.fullTeamName
            teamDivision?.text = team?.teamDivision
            teamConference?.text = team?.teamConference
            val logoUrl = String.format(Constants.TEAM_LOGO_PATH, team?.teamAbbreviation?.toLowerCase())
            Picasso.get().load(logoUrl).into(teamLogo)
        }
    }
}
interface OnTeamClickListener{
    fun onTeamClick(team: Team)
}