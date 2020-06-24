package pl.tw.counterserver.discovery.gossip

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component


@Component
class FailureDetector(private val nodesRepository: NodesRepository) {

    @Scheduled(fixedRateString = "\${failure.detection.interval.timeMillis:1000}")
    fun detect() {
//        nodesRepository.getRemoteNodes().forEach {
//            nodesRepository.updateNode(it.key, BiFunction<NodeAddress, NodeStatus?, NodeStatus?> { _, status ->
//                    null
//            })
//        }
//        nodesRepository.updateNode()
//        nodesRepository.updateNode()

    }
}