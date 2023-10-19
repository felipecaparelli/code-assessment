package com.caparelli.logger

import org.ehcache.event.CacheEvent
import org.ehcache.event.CacheEventListener
import org.slf4j.LoggerFactory

class CacheEventLogger : CacheEventListener<Any, Any> {
    private val logger = LoggerFactory.getLogger(CacheEventLogger::class.java)
    override fun onEvent(cacheEvent: CacheEvent<out Any, out Any>?) {
        logger.info("Cache Event ${cacheEvent!!.type}; Key ${cacheEvent.key}; Old value ${cacheEvent.oldValue}; New value ${cacheEvent.newValue}")
    }
}
