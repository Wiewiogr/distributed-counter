package pl.tw.counterserver.api

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import pl.tw.counterserver.CountingService
import java.util.*


@Controller("counter")
class ServerApi(private val countingService: CountingService) {

    @PostMapping("increment")
    fun incrementCounter(): ResponseEntity<String> {
        val uuid = UUID.randomUUID()
        countingService.recordIncrementation(uuid)
        return ResponseEntity.ok("ok")
    }

    @GetMapping("value")
    fun getValue() = ResponseEntity.ok(countingService.getValue())
}