package com.professional.dev

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class TrackerApiApplication

fun main(args: Array<String>) {
    SpringApplication.run(TrackerApiApplication::class.java, *args)
}
