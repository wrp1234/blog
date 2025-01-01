//package com.wrp.blog.common.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
///**
// * @author wrp
// * @since 2024-11-21 22:48
// **/
//@Configuration
//public class RedisConfig {
//
//    /**
//     * RedisTemplate配置
//     * @param redisConnectionFactory redis连接工厂
//     * @return redisTemplate实例
//     */
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(redisConnectionFactory);
//        //设置key序列化方式String
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        //设置value的序列化方式json
//        redisTemplate.setValueSerializer(new StringRedisSerializer());
//        //设置hash key序列化方式String
//        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//        //设置hash value序列化json
//        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
//        redisTemplate.afterPropertiesSet();
//        return redisTemplate;
//    }
//}
