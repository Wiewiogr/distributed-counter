package pl.tw.counterserver.notifications.cluster


class StaticClusterDiscovery(private val servers: List<String>) {
    fun getServers() = servers
}