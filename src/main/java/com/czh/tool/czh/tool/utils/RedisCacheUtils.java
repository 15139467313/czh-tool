package com.czh.tool.czh.tool.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Spring Redis 工具类
 *
 * @author czh
 * @since 1.0
 **/
@SuppressWarnings(value = {"unchecked", "rawtypes"})
@Component
public class RedisCacheUtils {

    @Autowired
    public RedisTemplate redisTemplate;

    /**
     * 缓存基本对象
     *
     * @param key   缓存键
     * @param value 缓存值
     * @param <T>   泛型
     */
    public <T> void setCacheObject(final String key, final T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 缓存基本对象并设置过期时间
     *
     * @param key      缓存键
     * @param value    缓存值
     * @param timeout  过期时间
     * @param timeUnit 时间单位
     * @param <T>      泛型
     */
    public <T> void setCacheObject(final String key, final T value, final Integer timeout, final TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    /**
     * 设置缓存过期时间
     *
     * @param key     缓存键
     * @param timeout 过期时间（秒）
     * @return 是否成功
     */
    public boolean expire(final String key, final long timeout) {
        return expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置缓存过期时间
     *
     * @param key     缓存键
     * @param timeout 过期时间
     * @param unit    时间单位
     * @return 是否成功
     */
    public boolean expire(final String key, final long timeout, final TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }

    /**
     * 获取缓存剩余过期时间
     *
     * @param key 缓存键
     * @return 剩余时间（秒）
     */
    public long getExpire(final String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * 判断 key 是否存在
     *
     * @param key 缓存键
     * @return true 存在 false 不存在
     */
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 获取缓存对象
     *
     * @param key 缓存键
     * @param <T> 泛型
     * @return 缓存值
     */
    public <T> T getCacheObject(final String key) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    /**
     * 删除单个对象
     *
     * @param key 缓存键
     * @return 是否成功
     */
    public boolean deleteObject(final String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 删除多个对象
     *
     * @param collection 缓存键集合
     * @return 是否成功
     */
    public boolean deleteObject(final Collection collection) {
        return redisTemplate.delete(collection) > 0;
    }

    /**
     * 缓存 List 数据
     *
     * @param key      缓存键
     * @param dataList 待缓存的 List 数据
     * @param <T>      泛型
     * @return 缓存的数据条数
     */
    public <T> long setCacheList(final String key, final List<T> dataList) {
        Long count = redisTemplate.opsForList().rightPushAll(key, dataList);
        return count == null ? 0 : count;
    }

    /**
     * 获取缓存的 List 数据
     *
     * @param key 缓存键
     * @param <T> 泛型
     * @return 缓存的 List 数据
     */
    public <T> List<T> getCacheList(final String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    /**
     * 缓存 Set 数据
     *
     * @param key     缓存键
     * @param dataSet 待缓存的 Set 数据
     * @param <T>     泛型
     * @return 缓存的 Set 数据操作对象
     */
    public <T> BoundSetOperations<String, T> setCacheSet(final String key, final Set<T> dataSet) {
        BoundSetOperations<String, T> setOperation = redisTemplate.boundSetOps(key);
        for (T t : dataSet) {
            setOperation.add(t);
        }
        return setOperation;
    }

    /**
     * 获取缓存的 Set 数据
     *
     * @param key 缓存键
     * @param <T> 泛型
     * @return 缓存的 Set 数据
     */
    public <T> Set<T> getCacheSet(final String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 缓存 Map 数据
     *
     * @param key     缓存键
     * @param dataMap 待缓存的 Map 数据
     * @param <T>     泛型
     */
    public <T> void setCacheMap(final String key, final Map<String, T> dataMap) {
        if (dataMap != null) {
            redisTemplate.opsForHash().putAll(key, dataMap);
        }
    }

    /**
     * 获取缓存的 Map 数据
     *
     * @param key 缓存键
     * @param <T> 泛型
     * @return 缓存的 Map 数据
     */
    public <T> Map<String, T> getCacheMap(final String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 往 Hash 中存入数据
     *
     * @param key   Redis 键
     * @param hKey  Hash 键
     * @param value 值
     * @param <T>   泛型
     */
    public <T> void setCacheMapValue(final String key, final String hKey, final T value) {
        redisTemplate.opsForHash().put(key, hKey, value);
    }

    /**
     * 获取 Hash 中的数据
     *
     * @param key  Redis 键
     * @param hKey Hash 键
     * @param <T>  泛型
     * @return Hash 中的数据
     */
    public <T> T getCacheMapValue(final String key, final String hKey) {
        HashOperations<String, String, T> opsForHash = redisTemplate.opsForHash();
        return opsForHash.get(key, hKey);
    }

    /**
     * 获取多个 Hash 中的数据
     *
     * @param key   Redis 键
     * @param hKeys Hash 键集合
     * @param <T>   泛型
     * @return Hash 对象集合
     */
    public <T> List<T> getMultiCacheMapValue(final String key, final Collection<Object> hKeys) {
        return redisTemplate.opsForHash().multiGet(key, hKeys);
    }

    /**
     * 删除 Hash 中的某条数据
     *
     * @param key  Redis 键
     * @param hKey Hash 键
     * @return 是否成功
     */
    public boolean deleteCacheMapValue(final String key, final String hKey) {
        return redisTemplate.opsForHash().delete(key, hKey) > 0;
    }

    /**
     * 获取缓存的基本对象列表
     *
     * @param pattern 字符串前缀
     * @return 对象列表
     */
    public Collection<String> keys(final String pattern) {
        return redisTemplate.keys(pattern);
    }
}
