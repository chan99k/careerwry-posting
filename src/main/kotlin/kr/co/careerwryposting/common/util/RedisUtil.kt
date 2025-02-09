package kr.co.careerwryposting.common.util

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service

@Service
class RedisUtil(
    private val redisTemplate: RedisTemplate<String, Any>,
) {
    fun setData(key: String, value: Any) {
        redisTemplate.opsForValue().set(key, value.toString())
    }

    fun getData(key: String): Any? {
        return redisTemplate.opsForValue().get(key)
    }

    fun increment(key: String) {
        redisTemplate.opsForValue().increment(key, 1L)
    }

    fun decrement(key: String) {
        redisTemplate.opsForValue().decrement(key, 1L)
    }

    fun getCount(key: String): Long? {
        return redisTemplate.opsForValue().get(key)?.toString()?.toLong()
    }

    fun getLikeCountKey(postToken: String): String {
        return "like:$postToken"
    }
}
