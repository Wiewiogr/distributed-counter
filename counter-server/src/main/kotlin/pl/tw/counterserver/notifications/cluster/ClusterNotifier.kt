package pl.tw.counterserver.notifications.cluster

import pl.tw.counterserver.discovery.ClusterDiscovery
import pl.tw.counterserver.notifications.Notifier
import java.util.*


class ClusterNotifier(private val notificationClient: NotificationClient,
                      private val clusterDiscovery: ClusterDiscovery) : Notifier {

    override fun notify(id: UUID) {
        clusterDiscovery
                .getServers()
                .forEach {
                    notificationClient.notify("$it/internal/notify", id)
                }
    }
}