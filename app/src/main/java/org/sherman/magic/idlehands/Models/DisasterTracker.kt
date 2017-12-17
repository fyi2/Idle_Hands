package org.sherman.magic.idlehands.Models

import android.app.Application
import io.realm.Realm

/**
 * Created by Admin on 12/16/2017.
 */
class DisasterTracker: Application() {
    override fun onCreate(){
        super.onCreate()
        Realm.init(this)
    }
}