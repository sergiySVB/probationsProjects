package ru.mail.sergey_balotnikov.taskapi.di.component

import dagger.Component
import dagger.Subcomponent
import ru.mail.sergey_balotnikov.taskapi.MainActivityRouter
import ru.mail.sergey_balotnikov.taskapi.di.scopes.ActivityScope
import ru.mail.sergey_balotnikov.taskapi.di.module.ActivityModule
import ru.mail.sergey_balotnikov.taskapi.di.module.DetailsModule
import ru.mail.sergey_balotnikov.taskapi.di.module.FilterModule
import ru.mail.sergey_balotnikov.taskapi.di.module.TeamsModule
import ru.mail.sergey_balotnikov.taskapi.ui.MainActivity
import ru.mail.sergey_balotnikov.taskapi.ui.Router
import ru.mail.sergey_balotnikov.taskapi.ui.teamList.model.Team
import ru.mail.sergey_balotnikov.taskapi.util.Constants
import javax.inject.Named

@ActivityScope
@Component(modules = [ActivityModule::class], dependencies = [AppComponent::class])
interface ActivityComponent {

//    fun plusTeams(teamsModule: TeamsModule): TeamsComponent
//    fun plusFilter(filterModule: FilterModule): FilterComponent
//    fun plusDetails(detailsModule: DetailsModule): DetailsComponent
    fun inject(activity: MainActivity)

    @Named("1")
    fun provideFilter(): Team

    @Named("2")
    fun provideSelectedTeam(): Team

    fun router(): Router

    fun router2(): MainActivityRouter
}