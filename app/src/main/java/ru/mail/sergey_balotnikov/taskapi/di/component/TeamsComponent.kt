package ru.mail.sergey_balotnikov.taskapi.di.component

import androidx.fragment.app.Fragment
import dagger.Component
import dagger.Subcomponent
import ru.mail.sergey_balotnikov.taskapi.di.module.TeamsModule
import ru.mail.sergey_balotnikov.taskapi.di.scopes.TeamsScope
import ru.mail.sergey_balotnikov.taskapi.ui.Router
import ru.mail.sergey_balotnikov.taskapi.ui.teamList.view.FragmentTeamList
import javax.inject.Inject
import javax.inject.Singleton

@TeamsScope
@Component(modules = [TeamsModule::class], dependencies = [ActivityComponent::class])
interface TeamsComponent {
    fun inject(fragment: FragmentTeamList)
}