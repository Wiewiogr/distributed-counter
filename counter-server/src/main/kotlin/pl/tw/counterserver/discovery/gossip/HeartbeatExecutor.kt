package pl.tw.counterserver.discovery.gossip

import org.springframework.scheduling.annotation.Scheduled
import java.util.concurrent.atomic.AtomicLong


class HeartbeatExecutor(private val nodesRepository: NodesRepository,
                        private val heartbeatClient: HeartbeatClient) {

    private val counter = AtomicLong()

    @Scheduled(fixedRateString = "\${gossip.interval.timeMillis:1000}")
    fun perform() {

        val nodes = nodesRepository.getRemoteNodes()

        val heartbeatInfo = nodes.map { HeartbeatMessage(it.key, it.value.counter) } +
                HeartbeatMessage(nodesRepository.getLocalNodeAddr(), counter.incrementAndGet())

        nodes.keys.forEach { heartbeatClient.sendHeartbeat(it, heartbeatInfo) }
    }
}