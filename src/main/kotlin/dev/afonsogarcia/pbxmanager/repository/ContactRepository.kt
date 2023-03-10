package dev.afonsogarcia.pbxmanager.repository

import dev.afonsogarcia.pbxmanager.model.Contact
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface ContactRepository : CoroutineCrudRepository<Contact, String>
