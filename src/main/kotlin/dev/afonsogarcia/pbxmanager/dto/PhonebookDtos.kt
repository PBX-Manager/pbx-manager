package dev.afonsogarcia.pbxmanager.dto

import jakarta.xml.bind.annotation.XmlAttribute
import jakarta.xml.bind.annotation.XmlElement
import jakarta.xml.bind.annotation.XmlRootElement
import jakarta.xml.bind.annotation.XmlType
import jakarta.xml.bind.annotation.XmlValue

@XmlRootElement(name = "PBXManagerIPPhoneDirectory")
data class PhoneDirectory (
    @get:XmlElement(name = "DirectoryEntry") var entries: List<DirectoryEntry> = emptyList()
)

@XmlType(propOrder = ["name", "telephone"])
data class DirectoryEntry (
    @get:XmlElement(name = "Name") var name: String = "",
    @get:XmlElement(name = "Telephone") var telephone: List<Telephone> = emptyList()
)

data class Telephone (
    @get:XmlAttribute(name = "label") var label: String = "",
    @get:XmlValue var phoneNumber: String = ""
)
