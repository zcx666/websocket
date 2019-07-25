package top.zhengchunxu.service;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import top.zhengchunxu.service.impl.AsyncServiceImpl;

@Component
public class RedisToken {

	@Autowired
	private AsyncService asyncService;
	@Autowired
	private AsyncServiceImpl template;
	private static final long TOKENTIMEOUUT=60*60;
	
	public String getToken() {
		String token="token"+UUID.randomUUID().toString();
		
		asyncService.setString(token, token, TOKENTIMEOUUT);
		return token;
	}
	
	
	public boolean findRedisToken(String tokenKey) {
		String string = (String) template.getString(tokenKey);
		if (StringUtils.isEmpty(string)) {
			return false;
		}
		template.deleKey(tokenKey);
		return true;
	}
	
	
	
}
