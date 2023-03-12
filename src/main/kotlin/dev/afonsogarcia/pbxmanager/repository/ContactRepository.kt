package dev.afonsogarcia.pbxmanager.repository

import dev.afonsogarcia.pbxmanager.model.Contact
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface ContactRepository : CoroutineCrudRepository<Contact, Int> {
    fun findAllByInternalExtensionNotNull(): Flow<Contact>
}
