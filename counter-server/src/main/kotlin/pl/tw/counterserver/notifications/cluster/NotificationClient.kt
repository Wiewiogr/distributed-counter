package pl.tw.counterserver.notifications.cluster

import org.springframework.web.client.RestTemplate
import pl.tw.counterserver.Event
import java.util.*


class NotificationClient(private val restTemplate: RestTemplate) {

    fun notify(endpoint: String, id: UUID) {
        println(endpoint)
        restTemplate.postForLocation(endpoint, Event(id))
    }
}