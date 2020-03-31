package ru.mail.sergey_balotnikov.taskapi

import android.util.Log
import ru.mail.sergey_balotnikov.taskapi.ui.MainActivity

class MainActivityRouter(private val activity: MainActivity) {

    fun goToDetails() {
        Log.d("Details", "Go to")
    }

}