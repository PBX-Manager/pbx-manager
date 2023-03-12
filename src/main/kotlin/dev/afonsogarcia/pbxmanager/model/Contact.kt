package dev.afonsogarcia.pbxmanager.model

import dev.afonsogarcia.pbxmanager.validation.PhoneNumber
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table(name = "contacts")
data class Contact (
    @Id val id: Int?,
    val name: String,
    @PhoneNumber("Internal Extension is not a valid phone number") val internalExtension: String? = null,
    @PhoneNumber("Office is not a valid phone number") val officeNumber: String? = null,
    @PhoneNumber("Office Mobile is not a valid phone number") val officeMobileNumber: String? = null,
    @PhoneNumber("Home is not a valid phone number") val homeNumber: String? = null,
    @PhoneNumber("Mobile is not a valid phone number") val mobileNumber: String? = null,
    @PhoneNumber("Other Number is not a valid phone number") val otherNumber: String? = null,
    val synced: Boolean
) {
    fun withName(name: String, synced: Boolean): Contact =
        Contact(
            id = this.id,
            name = name,
            internalExtension = this.internalExtension,
            homeNumber = this.homeNumber,
            mobileNumber = this.mobileNumber,
            officeNumber = this.officeNumber,
            officeMobileNumber = this.officeMobileNumber,
            otherNumber = this.otherNumber,
            synced = synced
        )

    fun excludeInternalExtension(synced: Boolean): Contact =
        Contact(
            id = this.id,
            name = this.name,
            internalExtension = null,
            homeNumber = this.homeNumber,
            mobileNumber = this.mobileNumber,
            officeNumber = this.officeNumber,
            officeMobileNumber = this.officeMobileNumber,
            otherNumber = this.otherNumber,
            synced = synced
        )

    fun removeId(synced: Boolean): Contact =
        Contact(
            id = null,
            name = this.name,
            internalExtension = this.internalExtension,
            homeNumber = this.homeNumber,
            mobileNumber = this.mobileNumber,
            officeNumber = this.officeNumber,
            officeMobileNumber = this.officeMobileNumber,
            otherNumber = this.otherNumber,
            synced = synced
        )
}
