package top.zhengchunxu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import top.zhengchunxu.session.STOMPConnectEventListener;
import top.zhengchunxu.session.SocketSessionRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig1 implements WebSocketMessageBrokerConfigurer{

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		
		registry.enableSimpleBroker("/queue12","/sub12");
		registry.setApplicationDestinationPrefixes("/request12");
		registry.setUserDestinationPrefix("/user");
	}
	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		
		registry.addEndpoint("/websocket12").setAllowedOrigins("*").withSockJS();
	}
	
	@Bean
	public SocketSessionRegistry socketSessionRegistry() {
		return new SocketSessionRegistry();
	}
	
	@Bean
	public STOMPConnectEventListener sTOMPConnectEventListener() {
		return new STOMPConnectEventListener();
	}
}
