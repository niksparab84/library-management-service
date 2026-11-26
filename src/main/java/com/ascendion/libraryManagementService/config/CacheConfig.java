package com.ascendion.libraryManagementService.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager caffeinecacheManager = new CaffeineCacheManager("bookCache");
        caffeinecacheManager.setCaffeine(caffeineCacheBuilder());
        return caffeinecacheManager;
    }

    @Bean
    public Caffeine<Object, Object> caffeineCacheBuilder() {
        return Caffeine.newBuilder()
                .initialCapacity(100)
                .maximumSize(500)
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .recordStats();
    }
}

    // @Bean vs @Component:
    // @Bean is used to define a bean in a configuration class, while @Component is used to mark a class as a Spring-managed component. @Bean is typically used for third-party classes or when you need to customize the bean creation process, while @Component is used for your own classes that you want Spring to manage automatically.
    // In this case, we use @Bean to define the CacheManager and Caffeine cache builder beans, as they are part of the Spring configuration and not our own classes.
    // The CacheManager bean is responsible for managing the cache, while the Caffeine cache builder bean is used to configure the cache properties such as initial capacity, maximum size, and expiration time.

    // @Qualifier is used to specify which bean to inject when there are multiple beans of the same type. In this case, we don't have multiple
    // beans of the same type, so we don't need to use @Qualifier.
    // in case of multiple datasources, we can use @Qualifier to specify which datasource bean to inject into a repository or service class.
    // For example, if we have two datasource beans named "dataSource1" and "dataSource2", we can use @Qualifier("dataSource1") to inject the
    // first datasource bean into a repository or service class.
    // Apart from Qualifier, we can also use @Primary to specify which bean should be used as the default when there are multiple beans of the
    // same type. For example, if we have two datasource beans named "dataSource1" and "dataSource2", we can annotate one of them with @Primary
    // to indicate that it should be used as the default datasource bean when no @Qualifier is specified.
    // @Primary is used to indicate that a bean should be given preference when multiple beans of the same type are available for injection.
    // When a bean is annotated with @Primary, it will be used as the default bean for injection when no specific bean is specified using
    // @Qualifier. This is useful when you have multiple beans of the same type and want to specify which one should be used by default.

