package pl.tw.counterserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CounterServerApplication

fun main(args: Array<String>) {
    runApplication<CounterServerApplication>(*args)
}
