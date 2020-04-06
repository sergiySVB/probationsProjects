package ru.mail.sergey_balotnikov.taskapi

import android.app.Application
import ru.mail.sergey_balotnikov.taskapi.di.component.*
import ru.mail.sergey_balotnikov.taskapi.di.module.*
import ru.mail.sergey_balotnikov.taskapi.ui.MainActivity

class NBAApp: Application() {

    companion object{
        private lateinit var appComponent: AppComponent
        fun appComponent() = appComponent
    }

//    private var appComponent: AppComponent? = null
//    private var activityComponent: ActivityComponent? = null
//    private var teamsComponent: TeamsComponent? = null
//    private var filterComponent: FilterComponent? = null
//    private var detailsComponent: DetailsComponent? = null

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().serviceModule(ServiceModule(this)).build()
        appComponent.inject(this)
    }

//    fun addActivityComponent(activity: MainActivity): ActivityComponent?{
//        if(activityComponent==null){
//            activityComponent = appComponent?.plusActivityComponent(ActivityModule(activity))
//        }
//        return activityComponent
//    }
//    fun addTeamsComponent(): TeamsComponent?{
//        if(teamsComponent==null){
//            teamsComponent = activityComponent?.plusTeams(TeamsModule())
//        }
//        return teamsComponent
//    }
//    fun addFilterComponent(): FilterComponent?{
//        if(filterComponent==null){
//            filterComponent = activityComponent?.plusFilter(FilterModule())
//        }
//        return filterComponent
//    }
//    fun addDetailsComponent(): DetailsComponent?{
//        if(detailsComponent==null){
//            detailsComponent = activityComponent?.plusDetails(DetailsModule())
//        }
//        return detailsComponent
//    }

//
//    fun clearActivityComponent(){
//        activityComponent = null
//    }
//    fun clearTeamsComponent(){
//        teamsComponent = null
//    }
//    fun clearFilterComponent(){
//        filterComponent = null
//    }
//    fun clearDetailsComponent(){
//        detailsComponent = null
//    }
}