package org.sherman.magic.idlehands.Models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

/**
 * Created by Admin on 12/16/2017.
 */
open class Disaster: RealmObject() {
    @PrimaryKey
    private var id = UUID.randomUUID().toString()
    var name: String = "Nakatomi Plaza"
    var longtitude: Double = 0.0
    var latitude: Double = 0.0
    var hashTags:String = ""
    var status:String = "Open"

    fun getID(): String {
        return id
    }

    override  fun toString() : String {
        return "Activity: $name"
    }
}