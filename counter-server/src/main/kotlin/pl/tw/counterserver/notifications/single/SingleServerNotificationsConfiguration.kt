package pl.tw.counterserver.notifications.single

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile


@Profile("single-server")
@Configuration
class SingleServerNotificationsConfiguration {

    @Bean
    fun notifier() = FakeNotifier()
}