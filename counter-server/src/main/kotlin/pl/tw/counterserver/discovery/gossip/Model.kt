package pl.tw.counterserver.discovery.gossip


typealias NodeAddress = String

data class NodeStatus(
        val counter: Long,
        val lastUpdateTimestamp: Long
)

data class HeartbeatMessage(
        val address: NodeAddress,
        val counter: Long
)