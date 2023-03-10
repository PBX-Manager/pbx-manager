package dev.afonsogarcia.pbxmanager.util

fun CharSequence.isPhoneNumber() : Boolean = this.matches("^\\+?\\d+\$".toRegex())
