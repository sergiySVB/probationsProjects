package ru.mail.sergey_balotnikov.taskapi.di.component

import dagger.Subcomponent
import ru.mail.sergey_balotnikov.taskapi.di.module.DetailsModule
import ru.mail.sergey_balotnikov.taskapi.di.scopes.DetailsScope

@DetailsScope
@Subcomponent(modules = [DetailsModule::class])
interface DetailsComponent {
}