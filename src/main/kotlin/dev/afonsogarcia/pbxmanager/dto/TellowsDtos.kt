package dev.afonsogarcia.pbxmanager.dto

data class TellowsResponse (
    val tellows: Tellows
)

data class Tellows (
    val number: String,
    val normalizedNumber: String,
    val score: String,
    val comments: String,
    val scoreColor: String,
    val scorePath: String,
    val location: String,
    val country: String,
    val callerTypes: CallerTypes?,
    val callerNames: CallerNames?
)

data class CallerTypes (
    val caller: List<CallerType>
)

data class CallerType (
    val name: String,
    val count: String
)

data class CallerNames (
    val caller: List<String>
)
