package pl.tw.counterserver.api

import org.springframework.context.annotation.Profile
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import pl.tw.counterserver.discovery.gossip.HeartbeatMessage
import pl.tw.counterserver.discovery.gossip.HeartbeatProcessor


@Controller
@Profile("gossip-discovery")
class HeartbeatsInternalApi(private val heartbeatProcessor: HeartbeatProcessor) {

    @PostMapping("internal/heartbeat")
    fun receiveNotification(@RequestBody info: List<HeartbeatMessage>): ResponseEntity<String> {
        println("Received heartbeat info")
        println(info)
        heartbeatProcessor.process(info)
        return ResponseEntity.ok("ok")
    }
}