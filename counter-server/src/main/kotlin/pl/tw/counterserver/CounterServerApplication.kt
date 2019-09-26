package pl.tw.counterserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class CounterServerApplication

fun main(args: Array<String>) {
    runApplication<CounterServerApplication>(*args)
}
