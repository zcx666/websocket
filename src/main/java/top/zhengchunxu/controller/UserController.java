package top.zhengchunxu.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import top.zhengchunxu.pojo.Shout;

@Controller
public class UserController {

	@RequestMapping("/send")
	public String zhong() {return "send1";}
	
	@RequestMapping("/revice")
	public String zhong1() {return "revice";}
	
	@MessageMapping("/send")
	@SendToUser("/queue/revice")
	public Shout zhong2(Shout shout,StompHeaderAccessor accessor) {
		
		System.out.println("接收到的用户名"+accessor.getUser().getName());
		return shout;
	}
	
}
