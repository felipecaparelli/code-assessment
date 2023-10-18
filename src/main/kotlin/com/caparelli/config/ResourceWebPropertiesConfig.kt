package com.caparelli.config

import org.springframework.boot.autoconfigure.web.WebProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class ResourceWebPropertiesConfig {
    @Bean
    fun resources(): WebProperties.Resources? {
        return WebProperties.Resources()
    }
}
