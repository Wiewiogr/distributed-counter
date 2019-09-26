package pl.tw.counterserver

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import java.util.function.Supplier


@Configuration
class ApplicationConfiguration {

    @Bean
    fun restTemplate() = RestTemplate()

    @Bean
    fun timeSupplier(): Supplier<Long> = Supplier { System.currentTimeMillis() }
}