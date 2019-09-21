package pl.tw.counterserver.notifications.cluster


interface ClusterDiscovery {
    fun getServers(): List<String>
}