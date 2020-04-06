package ru.mail.sergey_balotnikov.taskapi.di.component

import dagger.Component
import ru.mail.sergey_balotnikov.taskapi.NBAApp
import ru.mail.sergey_balotnikov.taskapi.di.api.NBAService
import ru.mail.sergey_balotnikov.taskapi.di.module.ActivityModule
import ru.mail.sergey_balotnikov.taskapi.di.module.ServiceModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ServiceModule::class])

interface AppComponent {
    fun inject(app: NBAApp)
    //fun plusActivityComponent(activityModule: ActivityModule): ActivityComponent
    fun provideService(): NBAService
}