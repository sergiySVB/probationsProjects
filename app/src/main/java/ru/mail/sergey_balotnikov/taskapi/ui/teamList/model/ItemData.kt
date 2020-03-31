package ru.mail.sergey_balotnikov.taskapi.ui.teamList.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Team(
    @SerializedName("id")
    var teamId:Int?,
    @SerializedName("abbreviation")
    var teamAbbreviation: String,
    @SerializedName("city")
    var teamCity: String,
    @SerializedName("conference")
    var teamConference: String,
    @SerializedName("division")
    var teamDivision: String,
    @SerializedName("full_name")
    var fullTeamName: String,
    @SerializedName("name")
    var teamName: String
): Serializable {

    fun filterMatcher(filter: Team): Boolean =
        filterByTeamAttribute(this.teamAbbreviation, filter.teamAbbreviation) &&
                filterByTeamAttribute(this.teamCity, filter.teamCity) &&
                filterByTeamAttribute(this.teamConference, filter.teamConference) &&
                filterByTeamAttribute(this.teamDivision, filter.teamDivision) &&
                filterByTeamAttribute(this.fullTeamName, filter.fullTeamName) &&
                filterByTeamAttribute(this.teamName, filter.teamName)

    private fun filterByTeamAttribute(teamAttribute: String, filterAttribute: String?)=
        if(isTeamAttributeExists(filterAttribute)){
            teamAttribute.toLowerCase().contains(filterAttribute?.toLowerCase().toString())
        } else {
            true
        }

    private fun isTeamAttributeExists(attribute: String?)=
        (attribute != "" && attribute != null)
}

data class Result(
    @SerializedName("data")
    val teams: List<Team>
)
