package pl.tw.counterserver.discovery.gossip

import java.util.function.BiFunction
import java.util.function.Supplier


class HeartbeatProcessor(private val nodesRepository: NodesRepository,
                         private val timeSupplier: Supplier<Long>) {

    fun process(info: List<HeartbeatMessage>) {
        info.forEach {
            nodesRepository.updateNode(
                    it.address,
                    BiFunction { _: String, value: NodeStatus? ->
                        if (value == null || it.counter > value.counter) {
                            NodeStatus(it.counter, timeSupplier.get())
                        } else {
                            value
                        }
                    }
            )
        }
    }
}