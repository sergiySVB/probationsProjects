package ru.mail.sergey_balotnikov.taskapi.teamDetails.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_player.view.*
import ru.mail.sergey_balotnikov.taskapi.R
import ru.mail.sergey_balotnikov.taskapi.teamDetails.model.data.Player
import java.util.zip.Inflater

class AdapterPlayers : RecyclerView.Adapter<AdapterPlayers.ItemPlayer>{
    constructor() : super()

    private var players = listOf<Player>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemPlayer(LayoutInflater.from(parent.context).inflate(R.layout.item_player, parent, false))

    override fun getItemCount(): Int = players.size

    override fun onBindViewHolder(holder: ItemPlayer, position: Int) {
        holder.bind(players[position])
    }

    fun addPlayersToList(players: List<Player>){
        this.players = players
        notifyDataSetChanged()
    }

    inner class ItemPlayer : RecyclerView.ViewHolder{
        private val name: TextView
        private val position: TextView

        constructor(itemView: View) : super(itemView){
            name = itemView.tvPlayerName
            position = itemView.tvPlayerPosition
        }
        @SuppressLint("SetTextI18n")
        fun bind(player: Player){
            name.text = """${player.first_name} ${player.last_name}"""
            position.text = player.position
        }
    }
}