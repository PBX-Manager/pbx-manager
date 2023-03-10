package dev.afonsogarcia.pbxmanager.service

import dev.afonsogarcia.pbxmanager.dto.DirectoryEntry
import dev.afonsogarcia.pbxmanager.dto.PhoneDirectory
import dev.afonsogarcia.pbxmanager.model.Contact
import dev.afonsogarcia.pbxmanager.repository.ContactRepository
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Service

@Service
class PhonebookService(
    private val contactRepository: ContactRepository
) {

    suspend fun getPhonebook(): PhoneDirectory {
        val contacts = contactRepository.findAll().toList().map {
            DirectoryEntry(
                name = it.name,
                telephone = it.phoneNumber
            )
        }
        return PhoneDirectory(contacts)
    }

    suspend fun createContact(contact: Contact): Contact = contactRepository.save(contact.apply { setNewContact() })

    suspend fun saveContact(contact: Contact): Contact = contactRepository.save(contact)
}
