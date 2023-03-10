package dev.afonsogarcia.pbxmanager.model

import dev.afonsogarcia.pbxmanager.validation.PhoneNumber
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table(name = "contacts")
data class Contact (
    @Id val id: Int,
    var name: String,
    @PhoneNumber("Internal Extension is not a valid phone number") var internalExtension: String?,
    @PhoneNumber("Office is not a valid phone number") var officeNumber: String?,
    @PhoneNumber("Office Mobile is not a valid phone number") var officeMobileNumber: String?,
    @PhoneNumber("Home is not a valid phone number") var homeNumber: String?,
    @PhoneNumber("Mobile is not a valid phone number") var mobileNumber: String?,
    @PhoneNumber("Other Number is not a valid phone number") var otherNumber: String?
)
