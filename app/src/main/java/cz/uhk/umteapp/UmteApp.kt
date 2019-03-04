package cz.uhk.umteapp

import android.app.Application
import cz.uhk.umteapp.prefs.Prefs

class UmteApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Prefs.init(applicationContext)
    }
}