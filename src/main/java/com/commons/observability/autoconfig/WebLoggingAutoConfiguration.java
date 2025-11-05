
package com.commons.observability.autoconfig;

import com.commons.observability.web.CorrelationAndAccessLogFilter;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

/**
 * -> Auto-configuration class for web logging and correlation ID propagation.
 *
 * This configuration is automatically loaded by Spring Boot if present on the classpath.
 * It registers filters and interceptors to:
 *   - Generate and propagate a Correlation ID (cid) across microservices.
 *   - Log request/response metadata consistently.
 */
@AutoConfiguration
public class WebLoggingAutoConfiguration {

    /**
     * -> Registers the CorrelationAndAccessLogFilter bean.
     *
     * - This filter generates a correlation ID (cid) for every incoming HTTP request
     *   if one is not already present, and logs request/response metadata.
     * - The filter is registered only if:
     *    1. No other CorrelationAndAccessLogFilter bean is already defined.
     *    2. Property `commons.logging.filter.enabled=true` (default: true).
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(
            name = "commons.logging.filter.enabled",
            havingValue = "true",
            matchIfMissing = true
    )
    public CorrelationAndAccessLogFilter correlationAndAccessLogFilter() {
        return new CorrelationAndAccessLogFilter();
    }

}
