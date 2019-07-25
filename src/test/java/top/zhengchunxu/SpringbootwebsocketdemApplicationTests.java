package top.zhengchunxu;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootwebsocketdemApplicationTests {
	private static final Logger log=LoggerFactory.getLogger(SpringbootwebsocketdemApplicationTests.class);

	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Test
	public void contextLoads() {
		
		stringRedisTemplate.opsForValue().set("zhong", "中国惊奇先生漫画");
	}

}
