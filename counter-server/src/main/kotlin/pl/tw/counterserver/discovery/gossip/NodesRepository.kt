package pl.tw.counterserver.discovery.gossip

import java.util.concurrent.ConcurrentHashMap
import java.util.function.BiFunction


class NodesRepository(private val localNodeAddress: String) {
    private val nodes: ConcurrentHashMap<NodeAddress, NodeStatus> = ConcurrentHashMap()

    fun getRemoteNodes(): Map<NodeAddress, NodeStatus> = nodes

    fun getLocalNodeAddr() = localNodeAddress

    fun updateNode(addr: NodeAddress, updater: BiFunction<NodeAddress, NodeStatus?, NodeStatus?>) =
            run { nodes.compute(addr, updater) }
}