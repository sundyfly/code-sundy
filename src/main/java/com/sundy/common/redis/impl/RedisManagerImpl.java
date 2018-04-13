package com.sundy.common.redis.impl;

import com.sundy.common.constant.Constant;
import com.sundy.common.redis.RedisManager;
import com.sundy.common.util.Base64Utils;
import com.sundy.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 通过Redis存储变化很小的数据表
 * @author sundy
 * @date 2018年04月10日 11:51
 */
@Component
public class RedisManagerImpl implements RedisManager {

    private JedisCluster jedisCluster;

    private RedisTemplate<String, String> redis;

    @Autowired
    public void setRedis(RedisTemplate<String, String> redis) {
        this.redis = redis;
        //泛型设置成Long后必须更改对应的序列化方案
        redis.setKeySerializer(new JdkSerializationRedisSerializer());
    }
    public JedisCluster getJedisCluster() {
        return jedisCluster;
    }

    public void setJedisCluster(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
    }

    @Override
    public String set(String key, String value) {
        redis.boundValueOps(key).set(value, Constant.TOKEN_EXPIRES_MINUTES, TimeUnit.MINUTES);
        return "";
    }

    @Override
    public String get(String key) {
        return redis.boundValueOps(key).get();
    }

    @Override
    public Boolean exists(String key) {
        String token = redis.boundValueOps(key).get();
        return StringUtils.notNull(token);
    }

    @Override
    public Long expire(String key, int seconds) {
        return jedisCluster.expire(key, seconds);
    }

    @Override
    public Long ttl(String key) {
        return jedisCluster.ttl(key);
    }

    @Override
    public Long incr(String key) {
        return jedisCluster.incr(key);
    }

    @Override
    public Long hset(String key, String field, String value) {
        return jedisCluster.hset(key, field, value);
    }

    @Override
    public String hget(String key, String field) {
        return jedisCluster.hget(key, field);
    }

    @Override
    public Long hdel(String key, String... field) {
        return jedisCluster.hdel(key, field);
    }

    @Override
    public Boolean hexists(String key, String field) {
        return jedisCluster.hexists(key, field);
    }

    @Override
    public List<String> hvals(String key) {
        return jedisCluster.hvals(key);
    }

    @Override
    public Long del(String key) {
        return jedisCluster.del(key);
    }
}
