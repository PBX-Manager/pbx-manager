package dev.afonsogarcia.pbxmanager.service

import dev.afonsogarcia.pbxmanager.model.CallHistory
import dev.afonsogarcia.pbxmanager.repository.CallHistoryRepository
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Service

@Service
class CallHistoryService (
    private val callHistoryRepository: CallHistoryRepository
) {
    suspend fun registerCallHistory(name: String, number: String? = null) {
        val callHistory = CallHistory(name = name, number = number)
        callHistoryRepository.save(callHistory)
    }

    suspend fun getCallHistory(): List<CallHistory> = callHistoryRepository.findAll().toList()
}
