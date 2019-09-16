package pl.tw.counterserver.notifications

import java.util.*


interface Notifier {
    fun notify(id: UUID)
}