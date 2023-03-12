package dev.afonsogarcia.pbxmanager.dto.freepbx

data class User (
    var name: String?,
    var password: String?,
    var outboundCid: String?,
    var ringtimer: Int?,
    var noanswer: String?,
    var noanswerDestination: String?,
    var noanswerCid: String?,
    var busyCid: String?,
    var sipname: String?,
    var extPassword: String?,
    var voicemail: String?,
)

data class CoreDevice (
    var deviceId: String?,
    var dial: String?,
    var devicetype: String?,
    var description: String?,
    var emergencyCid: String?
)