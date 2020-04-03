package cn.qing.tian.test.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by 16643 on 2019/10/18.
 */
@Service
public class RedisService {

    private static final Logger logger = LoggerFactory.getLogger(RedisService.class);

    private final JedisPool jedisPool;

    @Autowired
    public RedisService(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    //get
    public String get(String key) {
        Jedis jedis = null;
        String getValue = null;
        //使用Jedis的连接池
        try {
            jedis = jedisPool.getResource();
            getValue = jedis.get(key);

        } catch (Exception e) {
            logger.error("redis连接池异常：" + e.getMessage());
        } finally {
            //释放连接池资源
            if (jedis != null) {
                jedis.close();
            }
        }
        return getValue;
    }

    //set
    //持久化的键
    public String set(String key, String value) {
        Jedis jedis = null;
        String getValue = null;
        //使用Jedis的连接池
        try {
            jedis = jedisPool.getResource();
            getValue = jedis.set(key, value);

        } catch (Exception e) {
            logger.error("redis连接池异常：" + e.getMessage());
        } finally {
            //释放连接池资源
            if (jedis != null) {
                jedis.close();
            }
        }
        return getValue;
    }

    //设置时间的键
    public String setTime(String key, int seconds, String value) {
        Jedis jedis = null;
        String getValue = null;
        //使用Jedis的连接池
        try {
            jedis = jedisPool.getResource();
            getValue = jedis.setex(key, seconds, value);

        } catch (Exception e) {
            logger.error("redis连接池异常：" + e.getMessage());
        } finally {
            //释放连接池资源
            if (jedis != null) {
                jedis.close();
            }
        }
        return getValue;
    }

    //exists
    public boolean exists(String key) {
        Jedis jedis = null;
        boolean getValue = false;
        //使用Jedis的连接池
        try {
            jedis = jedisPool.getResource();
            getValue = jedis.exists(key);

        } catch (Exception e) {
            logger.error("redis连接池异常：" + e.getMessage());
        } finally {
            //释放连接池资源
            if (jedis != null) {
                jedis.close();
            }
        }
        return getValue;
    }

    //ttl
    public Long ttl(String key) {
        Jedis jedis = null;
        long getValue = 0;
        //使用Jedis的连接池
        try {
            jedis = jedisPool.getResource();
            getValue = jedis.ttl(key);

        } catch (Exception e) {
            logger.error("redis连接池异常：" + e.getMessage());
        } finally {
            //释放连接池资源
            if (jedis != null) {
                jedis.close();
            }
        }
        return getValue;
    }

    //del
    public Long del(String key) {
        Jedis jedis = null;
        long getValue = 0;
        //使用Jedis的连接池
        try {
            jedis = jedisPool.getResource();
            getValue = jedis.del(key);

        } catch (Exception e) {
            logger.error("redis连接池异常：" + e.getMessage());
        } finally {
            //释放连接池资源
            if (jedis != null) {
                jedis.close();
            }
        }
        return getValue;
    }
}
