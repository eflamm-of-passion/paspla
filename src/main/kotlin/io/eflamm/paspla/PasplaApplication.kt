package io.eflamm.paspla

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class PasplaApplication

fun main(args: Array<String>) {
	runApplication<PasplaApplication>(*args)
}
