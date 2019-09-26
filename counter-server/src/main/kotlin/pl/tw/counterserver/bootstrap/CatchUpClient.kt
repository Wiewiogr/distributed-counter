package pl.tw.counterserver.bootstrap

import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.RequestEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.net.URI
import java.util.*

@Component
class CatchUpClient(private val restTemplate: RestTemplate) {

    fun getEvents(endpoint: String): List<UUID> {
        return try {
            restTemplate.exchange(
                    RequestEntity<Any>(HttpMethod.GET, URI("$endpoint/internal/events")),
                    object : ParameterizedTypeReference<List<UUID>>() {}
            ).body!!
        } catch (e: Exception) {
            e.printStackTrace()
            listOf()
        }
    }
}