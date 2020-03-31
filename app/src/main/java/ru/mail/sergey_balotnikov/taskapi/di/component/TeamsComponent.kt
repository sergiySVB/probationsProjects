package ru.mail.sergey_balotnikov.taskapi.di.component

import androidx.fragment.app.Fragment
import dagger.Subcomponent
import ru.mail.sergey_balotnikov.taskapi.di.module.TeamsModule
import javax.inject.Singleton

@Singleton
@Subcomponent(modules = [TeamsModule::class])
interface TeamsComponent {
    fun inject(fragment: Fragment)
}