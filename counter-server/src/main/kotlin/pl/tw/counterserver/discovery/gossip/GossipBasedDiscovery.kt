package pl.tw.counterserver.discovery.gossip

import pl.tw.counterserver.discovery.ClusterDiscovery


class GossipBasedDiscovery(private val nodesRepository: NodesRepository) : ClusterDiscovery {
    override fun getServers() = nodesRepository.getRemoteNodes().keys
}