package pl.tw.counterserver

import org.springframework.stereotype.Component
import pl.tw.counterserver.notifications.Notifier
import java.util.*
import java.util.concurrent.ConcurrentSkipListSet


@Component
class CountingService(val notifier: Notifier) {
    private var eventsSet = ConcurrentSkipListSet<UUID>()
//    private var eventsSet = ConcurrentHashMap.newKeySet<UUID>().toMutableSet()

    fun recordIncrementation(eventId: UUID) {
        eventsSet.add(eventId)
        notifier.notify(eventId)
    }

    fun recordIncrementationNotification(eventId: UUID) {
        if (!eventsSet.add(eventId)) {
            println("duplicated uuid")
        }
    }

    fun getAllEvents() = eventsSet

    fun getValue() = eventsSet.size
}