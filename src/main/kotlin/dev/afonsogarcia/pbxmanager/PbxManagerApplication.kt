package dev.afonsogarcia.pbxmanager

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PbxManagerApplication

fun main(args: Array<String>) {
	runApplication<PbxManagerApplication>(*args)
}
