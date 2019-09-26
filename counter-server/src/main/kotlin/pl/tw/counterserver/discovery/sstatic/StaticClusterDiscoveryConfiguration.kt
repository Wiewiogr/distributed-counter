package pl.tw.counterserver.discovery.sstatic

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import pl.tw.counterserver.discovery.ClusterDiscovery


@Configuration
@Profile("static-discovery")
class StaticClusterDiscoveryConfiguration {

    @Bean
    fun clusterDiscovery(@Value("\${cluster.servers.list:}") servers: List<String>): ClusterDiscovery =
            StaticClusterDiscovery(servers)
}