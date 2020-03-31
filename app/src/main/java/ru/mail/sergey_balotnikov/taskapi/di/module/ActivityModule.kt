package ru.mail.sergey_balotnikov.taskapi.di.module

import dagger.Module
import dagger.Provides
import ru.mail.sergey_balotnikov.taskapi.di.scopes.ActivityScope
import ru.mail.sergey_balotnikov.taskapi.ui.MainActivity
import ru.mail.sergey_balotnikov.taskapi.ui.Router
import ru.mail.sergey_balotnikov.taskapi.ui.teamList.model.Team
import ru.mail.sergey_balotnikov.taskapi.util.Constants
import javax.inject.Named
import ru.mail.sergey_balotnikov.taskapi.MainActivityRouter as MainActivityRouter1


@Module
class ActivityModule (private val activity: Router) {

    var filter: Team = Team(null, "","","","","","")
    var selectedTeam: Team = Team(null, "","","","","","")

    @Provides
    @ActivityScope
    fun provideRouter(): Router {
        return activity
    }

    @Named(Constants.SCOPE_NAMED_FILTER)
    @ActivityScope
    @Provides
    fun provideFilter() = filter

    @Named(Constants.SCOPE_NAMED_SELECTED)
    @Provides
    @ActivityScope
    fun provideSelected(): Team = selectedTeam

    @Provides
    @ActivityScope
    fun provideRoutez(): MainActivityRouter1 {
        return MainActivityRouter1(activity as MainActivity)
    }

}