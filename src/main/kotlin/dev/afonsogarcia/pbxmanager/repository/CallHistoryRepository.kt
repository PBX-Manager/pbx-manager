package dev.afonsogarcia.pbxmanager.repository

import dev.afonsogarcia.pbxmanager.model.CallHistory
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface CallHistoryRepository : CoroutineCrudRepository<CallHistory, Int>
