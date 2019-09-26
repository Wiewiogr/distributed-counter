package pl.tw.counterserver.notifications.single

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import pl.tw.counterserver.discovery.ClusterDiscovery


@Profile("single-server")
@Configuration
class SingleServerNotificationsConfiguration {

    @Bean
    fun notifier() = FakeNotifier()

    @Bean
    fun discovery(): ClusterDiscovery = object : ClusterDiscovery {
        override fun getServers(): Collection<String> = listOf()
    }
}