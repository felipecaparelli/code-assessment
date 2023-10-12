package com.caparelli

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class WeatherApplication : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        // nothing here
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(WeatherApplication::class.java, *args)
        }
    }
}
