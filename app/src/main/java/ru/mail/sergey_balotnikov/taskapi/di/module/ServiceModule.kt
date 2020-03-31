package ru.mail.sergey_balotnikov.taskapi.di.module

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.mail.sergey_balotnikov.taskapi.NBAApp
import ru.mail.sergey_balotnikov.taskapi.di.api.NBAService
import ru.mail.sergey_balotnikov.taskapi.util.Constants
import javax.inject.Singleton

@Module
class ServiceModule (private val app: NBAApp){

    @Provides
    @Singleton
    fun provideService(): NBAService {
        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addCallAdapterFactory(RxThreadCallAdapter(Schedulers.io(), AndroidSchedulers.mainThread()))
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()

        return retrofit.create(NBAService::class.java)
    }
}