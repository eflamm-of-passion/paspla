package io.eflamm.paspla

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PasplaApplication

fun main(args: Array<String>) {
	runApplication<PasplaApplication>(*args)
}
