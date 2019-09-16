package pl.tw.counterserver.notifications.single

import pl.tw.counterserver.notifications.Notifier
import java.util.*


class FakeNotifier : Notifier {
    override fun notify(id: UUID) {
        println("There are no other servers")
    }
}