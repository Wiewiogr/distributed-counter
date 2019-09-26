package pl.tw.counterserver.api

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import pl.tw.counterserver.CountingService
import pl.tw.counterserver.Event
import pl.tw.counterserver.discovery.ClusterDiscovery
import java.util.*


@Controller
class InternalServerApi(private val countingService: CountingService,
                        private val discovery: ClusterDiscovery) {

    @PostMapping("internal/notify")
    fun receiveNotification(@RequestBody event: Event): ResponseEntity<String> {
        countingService.recordIncrementationNotification(event.id)
        return ResponseEntity.ok("ok")
    }

    @GetMapping("internal/events")
    fun getAllEvents(): ResponseEntity<Collection<UUID>> {
        return ResponseEntity.ok(countingService.getAllEvents())
    }

    @GetMapping("internal/nodes")
    fun getNodes(): ResponseEntity<Collection<String>> {
        return ResponseEntity.ok(discovery.getServers())
    }
}

