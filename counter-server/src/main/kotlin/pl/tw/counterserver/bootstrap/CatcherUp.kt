package pl.tw.counterserver.bootstrap

import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component
import pl.tw.counterserver.CountingService
import pl.tw.counterserver.discovery.ClusterDiscovery


@Component
class CatcherUp(private val clusterDiscovery: ClusterDiscovery,
                private val catchUpClient: CatchUpClient,
                private val countingService: CountingService) : ApplicationListener<ContextRefreshedEvent> {

    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        clusterDiscovery
                .getServers()
                .flatMap { catchUpClient.getEvents(it) }
                .forEach { countingService.recordIncrementationNotification(it) }
    }
}