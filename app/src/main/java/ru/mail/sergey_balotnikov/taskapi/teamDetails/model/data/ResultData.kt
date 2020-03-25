package ru.mail.sergey_balotnikov.taskapi.teamDetails.model.data

import com.google.gson.annotations.SerializedName
import ru.mail.sergey_balotnikov.taskapi.teamList.model.Team

data class Player(
    val team: Team,
    val id: Int,
    val first_name: String,
    val last_name: String,
    val position: String
)
data class Meta(
    val next_page: Int?,
    val current_page: Int
)

data class ResultPlayers(
    @SerializedName("data")
    val players: List<Player>,
    @SerializedName("meta")
    val meta: Meta

)