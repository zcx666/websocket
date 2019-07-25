package top.zhengchunxu.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import top.zhengchunxu.service.RedisToken;
import top.zhengchunxu.service.impl.AsyncServiceImpl;
import top.zhengchunxu.tool.ConstantUtils;
import top.zhengchunxu.tool.ExtApiIdenpotent;

@Controller
public class testController {
 
	@Autowired
	private  RedisToken redisToken;
	
	    //如何使用Token解决幂等性
		//步骤：
		//1、在调用接口之前，生成(token) ,放在redis中，
	@RequestMapping("/getToken")
	public String zhong1() {
		String token = redisToken.getToken();
		return token;
	}
		
	@RequestMapping(value="/addProduct",produces="application/json;charset=utf-8")
	@ExtApiIdenpotent(type=ConstantUtils.EXTAPIHEAD)
	public String zhong(HttpServletRequest request) {
		
	
		//正常的业务逻辑
		
		return null;
	}
	
	
}
