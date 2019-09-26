package pl.tw.counterserver.discovery.gossip

import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent


class SeedGossiper(private val heartbeatClient: HeartbeatClient,
                   private val seedList: List<NodeAddress>,
                   private val localAddress: NodeAddress) : ApplicationListener<ContextRefreshedEvent> {

    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        println("DUUuuUUUUUUUUUUpa")

        for (i in 1..3) {
            val count = seedList
                    .map {
                        heartbeatClient.sendHeartbeat(it, listOf(HeartbeatMessage(localAddress, 0)))
                    }
                    .filter { it }
                    .count()

            if (count > 0) {
                return
            }
            println("Retrying heartbeats in ${2 * i * 2000L}ms")
            Thread.sleep(2 * i * 2000L)
        }

        System.exit(1)
    }
}
