package pl.tw.counterserver.discovery.sstatic

import pl.tw.counterserver.discovery.ClusterDiscovery


class StaticClusterDiscovery(private val servers: List<String>) : ClusterDiscovery {

    override fun getServers() = servers
}