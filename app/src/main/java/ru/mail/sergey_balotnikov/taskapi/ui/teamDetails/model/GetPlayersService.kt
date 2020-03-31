package ru.mail.sergey_balotnikov.nbateam.model.services

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import ru.mail.sergey_balotnikov.taskapi.ui.teamDetails.model.data.ResultPlayers
import ru.mail.sergey_balotnikov.taskapi.util.Constants

interface GetPlayersService {

    @GET("players")
    fun search(@Header (Constants.RAPID_API_HOST_KEY)
               host : String=Constants.RAPID_API_HOST,
               @Header (Constants.RAPID_API_KEY)
               key : String = Constants.RAPID_KEY,
               @Query("per_page")
               perPage: Int = 100,
               @Query("page")
               page: Int = 1
    ): Single<ResultPlayers>

    companion object Factory {
        fun create(): GetPlayersService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addCallAdapterFactory(RxThreadCallAdapter(Schedulers.io(), AndroidSchedulers.mainThread()))
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build()

            return retrofit.create(GetPlayersService::class.java)
        }
    }
}