package top.zhengchunxu.service.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import freemarker.template.Template;
import top.zhengchunxu.service.AsyncService;

@Service
public class AsyncServiceImpl implements AsyncService {
	
	@Autowired
	private StringRedisTemplate template;

	@Override
	@Async //声明使用异步调用
	public void generateReport() {
		//打印异步线程名称
		System.out.println("报表线程名称："+"【"+Thread.currentThread().getName()+"】");
       
	}

	//保存uuid数据
	@Override
	public void setString(String key, Object value, Long timeOut) {
		// TODO Auto-generated method stub
		if (value instanceof String) {
			String data=(String) value;
			template.opsForValue().set(key, data);
		}
		if (timeOut !=null) {
			template.expire(key, timeOut, TimeUnit.SECONDS);
		}
	}

	public Object getString(String key) {
		return template.opsForValue().get(key);
	}
	
	public void deleKey(String key) {
		template.delete(key);
	}
}
