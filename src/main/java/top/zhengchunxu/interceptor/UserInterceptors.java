package top.zhengchunxu.interceptor;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;

@Component
public class UserInterceptors implements ChannelInterceptor {

	@Value("${rabbitmq.userName}")
	private String stringValue;
	
	@Override
	public Message<?> preSend(Message<?> message, MessageChannel channel) {
		StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
		System.err.println("不是首次登陆"+StompCommand.CONNECT);
		System.out.println("不是首次登陆"+accessor.getCommand());
		//1、判断是不是首次登陆
		if (StompCommand.CONNECT.equals(accessor.getCommand())) {
			String username=accessor.getNativeHeader("username").get(0);
			String password=accessor.getNativeHeader("password").get(0);
			     if (username.equals("admin") && password.equals("admin")) {
					  Principal principal=new Principal() {						
						@Override
						public String getName() {
							// TODO Auto-generated method stub
							return stringValue;
						}
					};
					
					accessor.setUser(principal);
					return message;
				} else {
                      return null;
				}
		}
		
		//不是首次登陆，直接返回信息
		System.err.println("不是首次登陆"+message);
		return message;
	}
}
