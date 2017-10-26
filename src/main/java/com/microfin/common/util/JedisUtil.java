package com.microfin.common.util;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

/**
 * Created by manxiaolei on 2017/10/25.
 */
public class JedisUtil {
	/**取得redis实例*/
	static RedisTemplate<String, String> redisTemplate = new ClassPathXmlApplicationContext("spring-redis.xml").getBean("redisStringTemplate",
				 RedisTemplate.class);
	static RedisTemplate<String, Object> redisTemplateOb = new ClassPathXmlApplicationContext("spring-redis.xml").getBean("redisStringTemplate",
			RedisTemplate.class);

	/**
	 * 设置缓存key-value value为Object
	 * @param key
	 * @param value
	 */
	public static void setValue(String key, Object value) {
		// 取得redis操作
		ValueOperations<String, Object> valueOP = redisTemplateOb.opsForValue();
		valueOP.set(key, value);
	}

	/**
	 * 取得key的值
	 * @param key
	 * @return
	 */
	public static Object getValue(String key) {
		ValueOperations<String, Object> valueOP = redisTemplateOb.opsForValue();
		Object result = null;
		if (valueOP.get(key) != null) {
			result = valueOP.get(key);
		}
		return result;
	}

	/**
	 * 取得key的值，value为String类型的
	 * @param key
	 * @return
	 */
	public static Object getStringValue(String key) {
		ValueOperations<String, String> valueOP = redisTemplate.opsForValue();
		String result = null;
		if (valueOP.get(key) != null) {
			result = valueOP.get(key);
		}
		return result;
	}

	/**
	 * 增加key的value 增长量为 num
	 *
	 * @param key
	 * @param num
	 * @return
	 */
	public static long increaseNum(String key, long num) {
		ValueOperations<String, Object> valueOP = redisTemplateOb.opsForValue();
		return valueOP.increment(key, num);
	}

	/**
	 * 设置key失效时间
	 *
	 * @param key
	 * @param timeout
	 * @param unit
	 */
	public static void expire(String key, long timeout, TimeUnit unit){
		redisTemplateOb.expire(key,timeout,unit);
	}
}
