package pl.tw.counterserver.discovery.gossip

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.web.client.RestTemplate
import java.util.function.Supplier


@Configuration
@Profile("gossip-discovery")
class GossipDiscoveryConfiguration {

    @Bean
    fun nodesRepository(@Value("\${application.self.address}") addr: String): NodesRepository {
        return NodesRepository(addr)
    }

    @Bean
    fun heartbeatClient(restTemplate: RestTemplate) = HeartbeatClient(restTemplate)

    @Bean
    @Profile("!seed-node")
    fun seedGossiper(@Value("\${application.self.address}") addr: String,
                     @Value("\${cluster.servers.seed.isSeedNode}") isSeedNode: Boolean,
                     heartbeatClient: HeartbeatClient,
                     @Value("\${cluster.servers.seed.list}") seedList: List<String>): SeedGossiper {
        if (seedList.isEmpty()) {
            println("Seed list cannot be empty")
            System.exit(1)
        }
        return SeedGossiper(heartbeatClient, seedList, addr)
    }

    @Bean
    fun heartbeatExecutor(nodesRepository: NodesRepository, heartbeatClient: HeartbeatClient) =
            HeartbeatExecutor(nodesRepository, heartbeatClient)

    @Bean
    fun heartbeatProcessor(nodesRepository: NodesRepository, timeSupplier: Supplier<Long>) =
            HeartbeatProcessor(nodesRepository, timeSupplier)

    @Bean
    fun gossipBasedDiscovery(nodesRepository: NodesRepository) = GossipBasedDiscovery(nodesRepository)

}