package pl.tw.counterserver.notifications.cluster

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.web.client.RestTemplate
import pl.tw.counterserver.discovery.ClusterDiscovery
import pl.tw.counterserver.discovery.StaticClusterDiscovery


@Profile("!single-server")
@Configuration
class ClusterNotificationsConfiguration {

    @Bean
    fun restTemplate() = RestTemplate()

    @Bean
    fun notificationClient(restTemplate: RestTemplate) = NotificationClient(restTemplate)

    @Bean
    fun clusterDiscovery(@Value("\${cluster.servers.list:}") servers: List<String>): ClusterDiscovery =
            StaticClusterDiscovery(servers)

    @Bean
    fun notifier(notificationClient: NotificationClient,
                 staticClusterDiscovery: ClusterDiscovery) =
            ClusterNotifier(
                    notificationClient,
                    staticClusterDiscovery
            )
}