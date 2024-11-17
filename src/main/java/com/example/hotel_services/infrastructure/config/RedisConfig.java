package com.example.hotel_services.infrastructure.config;

import com.example.hotel_services.infrastructure.entities.Hotel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

@Configuration
public class RedisConfig {
    @Bean
    @Primary
    public ReactiveRedisConnectionFactory reactiveRedisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("cache", 6379);
        return new LettuceConnectionFactory(config);
    }

    @Bean
    public ReactiveRedisOperations<String, Hotel> redisOperations(ReactiveRedisConnectionFactory factory) {
        Jackson2JsonRedisSerializer<Hotel> serializer = new Jackson2JsonRedisSerializer<>(Hotel.class);

        RedisSerializationContext.RedisSerializationContextBuilder<String, Hotel> builder =
        RedisSerializationContext.newSerializationContext(new Jackson2JsonRedisSerializer<>(Hotel.class));

        RedisSerializationContext<String, Hotel> context = builder.value(serializer).build();

        return new ReactiveRedisTemplate<>(factory, context);
    }
}
