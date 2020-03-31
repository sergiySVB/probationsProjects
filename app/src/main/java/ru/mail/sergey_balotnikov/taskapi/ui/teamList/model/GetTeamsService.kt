package ru.mail.sergey_balotnikov.taskapi.ui.teamList.model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import ru.mail.sergey_balotnikov.taskapi.util.Constants

interface GetTeamsService {

    @GET("teams")
    fun search(@Header (Constants.RAPID_API_HOST_KEY) host:String=Constants.RAPID_API_HOST,
               @Header (Constants.RAPID_API_KEY) key:String = Constants.RAPID_KEY):Single<Result>

    companion object Factory {
        fun create(): GetTeamsService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addCallAdapterFactory(RxThreadCallAdapter(Schedulers.io(), AndroidSchedulers.mainThread()))
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build()

            return retrofit.create(GetTeamsService::class.java)
        }
    }
}