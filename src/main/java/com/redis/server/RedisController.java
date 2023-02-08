package com.redis.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RedisController {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @PostMapping("/redis")
    @ResponseBody
    public ResponseEntity<?> addRedisKey(String key, String value) {
        ValueOperations<String, String> vop = redisTemplate.opsForValue();
        vop.set(key,value);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/redis/{key}")
    public ResponseEntity<?> getRedisKey(@PathVariable String key) {
        ValueOperations<String, String> vop = redisTemplate.opsForValue();
        String value = vop.get(key);
        return new ResponseEntity<>(value, HttpStatus.OK);
    }


}
