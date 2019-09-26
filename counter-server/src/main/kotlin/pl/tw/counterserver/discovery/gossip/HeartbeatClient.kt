package pl.tw.counterserver.discovery.gossip

import org.slf4j.LoggerFactory
import org.springframework.http.HttpMethod
import org.springframework.http.RequestEntity
import org.springframework.web.client.RestTemplate
import java.net.URI


class HeartbeatClient(private val restTemplate: RestTemplate) {

    fun sendHeartbeat(server: String, info: List<HeartbeatMessage>): Boolean {
        val request = RequestEntity(
                info,
                HttpMethod.POST,
                URI("$server/internal/heartbeat")
        )

        LOGGER.info("Sending heartbeat to $server ($info)")

        return try {
            restTemplate.exchange(request, String::class.java)
            true
        } catch (e: Exception) {
            println("error occured during heartbeat")
            e.printStackTrace()
            false
        }
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(HeartbeatClient::class.java)
    }
}