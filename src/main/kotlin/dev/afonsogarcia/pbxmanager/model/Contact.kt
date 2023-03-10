package dev.afonsogarcia.pbxmanager.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table

@Table(name = "contacts")
data class Contact (
    var name: String,
    @Id var phoneNumber: String
) : Persistable<String> {

    @JsonIgnore @Transient var newContact: Boolean = false;

    @JsonIgnore override fun getId(): String = phoneNumber

    @JsonIgnore override fun isNew(): Boolean = newContact

    fun setNewContact() {
        newContact = true
    }
}
