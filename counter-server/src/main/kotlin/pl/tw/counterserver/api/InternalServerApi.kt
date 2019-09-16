package pl.tw.counterserver.api

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import pl.tw.counterserver.CountingService
import pl.tw.counterserver.Event


@Controller
class InternalServerApi(private val countingService: CountingService) {

    @PostMapping("internal/notify")
    fun receiveNotification(@RequestBody event: Event): ResponseEntity<String> {
        countingService.recordIncrementationNotification(event.id)
        return ResponseEntity.ok("ok")
    }
}

