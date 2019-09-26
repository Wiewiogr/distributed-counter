package pl.tw.counterserver.discovery


interface ClusterDiscovery {
    fun getServers(): Collection<String>
}