package ru.mail.sergey_balotnikov.taskapi.di.component

import dagger.Subcomponent
import ru.mail.sergey_balotnikov.taskapi.MainActivityRouter
import ru.mail.sergey_balotnikov.taskapi.di.scopes.ActivityScope
import ru.mail.sergey_balotnikov.taskapi.di.module.ActivityModule
import ru.mail.sergey_balotnikov.taskapi.di.module.DetailsModule
import ru.mail.sergey_balotnikov.taskapi.di.module.FilterModule
import ru.mail.sergey_balotnikov.taskapi.di.module.TeamsModule
import ru.mail.sergey_balotnikov.taskapi.ui.MainActivity

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {

    fun plusTeams(teamsModule: TeamsModule): TeamsComponent
    fun plusFilter(filterModule: FilterModule): FilterComponent
    fun plusDetails(detailsModule: DetailsModule): DetailsComponent
    fun inject(activity: MainActivity)

    fun router(): MainActivityRouter
}