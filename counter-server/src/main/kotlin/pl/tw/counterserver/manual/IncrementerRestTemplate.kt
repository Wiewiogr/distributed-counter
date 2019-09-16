package pl.tw.counterserver.manual

import org.springframework.web.client.RestTemplate
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {

    val servers = listOf(
            "http://localhost:8080",
            "http://localhost:8081",
            "http://localhost:8082"
    )
    val executorService = Executors.newFixedThreadPool(30)
            .apply {
                for (i in 1..60) {
                    this.submit {
                        val restTemplate = RestTemplate()
                        try {
                            for (j in 1..1000) {
                                val endpoint = "${servers[i % 3]}/increment"
                                restTemplate.postForLocation(endpoint, {})
                            }

                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            }

    executorService.shutdown()
    executorService.awaitTermination(60, TimeUnit.SECONDS)
    println("finished")
}


