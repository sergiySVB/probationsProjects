package ru.mail.sergey_balotnikov.taskapi.di.module

import androidx.annotation.NonNull
import dagger.Module
import dagger.Provides
import ru.mail.sergey_balotnikov.taskapi.di.scopes.ActivityScope
import ru.mail.sergey_balotnikov.taskapi.ui.MainActivity
import ru.mail.sergey_balotnikov.taskapi.ui.Router
import ru.mail.sergey_balotnikov.taskapi.ui.teamList.model.Team
import ru.mail.sergey_balotnikov.taskapi.util.Constants
import javax.inject.Inject
import javax.inject.Named
import ru.mail.sergey_balotnikov.taskapi.MainActivityRouter as MainActivityRouter1


@Module
class ActivityModule (private val activity: Router) {

    var filter: Team = Team(null, "","","","","","")
    var selectedTeam: Team = Team(null, "","","","","","")

    @Provides
    @ActivityScope
    fun provideRouter(): Router {
        return activity as Router
    }


    @ActivityScope
    @Provides
    @Named("1")
    fun provideFilter(): Team {
        return this.filter
    }

    @ActivityScope
    @Provides
    @Named("2")
    fun provideSelected(): Team {
        return this.selectedTeam
    }

    @Provides
    @ActivityScope
    fun provideRoutez(): MainActivityRouter1 {
        return MainActivityRouter1(activity as MainActivity)
    }

}