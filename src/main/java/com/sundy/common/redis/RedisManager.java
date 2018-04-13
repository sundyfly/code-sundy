package com.sundy.common.redis;

import java.util.List;

/**
 * 通过Redis存储变化很小的数据表
 * @author sundy
 * @date 2018年04月10日 11:51
 */
public interface RedisManager {
    //   user:id:1:name:zzy:age:18:date:2017作为key,value就是你整个数据内容了。
    //   keys(user:*age:18*),
    String set(String key, String value);

    String get(String key);

    Boolean exists(String key);

    Long expire(String key, int seconds);

    Long ttl(String key);

    Long incr(String key);

    Long hset(String key, String field, String value);

    String hget(String key, String field);

    Long hdel(String key, String... field);

    Boolean hexists(String key, String field);

    List<String> hvals(String key);

    Long del(String key);
}
