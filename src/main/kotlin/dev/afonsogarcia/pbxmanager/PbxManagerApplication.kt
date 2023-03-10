package dev.afonsogarcia.pbxmanager

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class PbxManagerApplication

fun main(args: Array<String>) {
	runApplication<PbxManagerApplication>(*args)
}
