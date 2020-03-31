package ru.mail.sergey_balotnikov.taskapi.di.api

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import ru.mail.sergey_balotnikov.taskapi.ui.teamDetails.model.data.ResultPlayers
import ru.mail.sergey_balotnikov.taskapi.ui.teamList.model.GetTeamsService
import ru.mail.sergey_balotnikov.taskapi.ui.teamList.model.Result
import ru.mail.sergey_balotnikov.taskapi.util.Constants

interface NBAService {
    //get a list of players request
    @GET("players")
    fun search(@Header(Constants.RAPID_API_HOST_KEY)
               host : String= Constants.RAPID_API_HOST,
               @Header(Constants.RAPID_API_KEY)
               key : String = Constants.RAPID_KEY,
               @Query("per_page")
               perPage: Int = 100,
               @Query("page")
               page: Int = 1
    ): Single<ResultPlayers>

    //get a list of teams request
    @GET("teams")
    fun search(@Header (Constants.RAPID_API_HOST_KEY) host:String=Constants.RAPID_API_HOST,
               @Header (Constants.RAPID_API_KEY) key:String = Constants.RAPID_KEY):Single<Result>
}