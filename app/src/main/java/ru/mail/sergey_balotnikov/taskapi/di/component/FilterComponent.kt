package ru.mail.sergey_balotnikov.taskapi.di.component

import dagger.Subcomponent
import ru.mail.sergey_balotnikov.taskapi.di.module.FilterModule
import ru.mail.sergey_balotnikov.taskapi.di.scopes.FilterScope

@FilterScope
@Subcomponent(modules = [FilterModule::class])
interface FilterComponent {
}