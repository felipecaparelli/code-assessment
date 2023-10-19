package com.caparelli.config

import com.github.benmanes.caffeine.cache.Caffeine
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.caffeine.CaffeineCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Duration

@Configuration
@EnableCaching
class CacheConfig {
    @Bean
    fun cacheManager(): CacheManager? {
        val cacheManager = CaffeineCacheManager("weatherCache") // Define your cache names here
        caffeineCacheBuilder()?.let { cacheManager.setCaffeine(it) }
        return cacheManager
    }

    fun caffeineCacheBuilder(): Caffeine<Any?, Any?>? {
        return Caffeine.newBuilder()
            .initialCapacity(100)
            .maximumSize(500)
            .expireAfterWrite(Duration.ofMinutes(5))
    }
}
