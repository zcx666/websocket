package top.zhengchunxu.config;

import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/*@Configuration
@EnableWebSocketMessageBroker*/
public class WebsocketConfig  implements WebSocketMessageBrokerConfigurer{
	
/*	@Autowired
	private UserInterceptors userInterceptors;

     @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
    	
    	 registry.addEndpoint("/websocket").setAllowedOrigins("*").withSockJS();
    }

     @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
    	
    	 registry.enableSimpleBroker("/queue","/sub");
    	 registry.setApplicationDestinationPrefixes("/request");
    	 registry.setUserDestinationPrefix("/user");
    }
     
     *//**
      * 输入通道参数设置
      * *//*
     @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
    	
    	 registration.taskExecutor()
    	             .corePoolSize(4) //设置消息输入通道的线程池线程数
    	             .maxPoolSize(8)  //最大线程数
    	             .keepAliveSeconds(60); //线程活动时间
    	 registration.interceptors(userInterceptors);
    }
     
     *//**
      * 消息传输参数配置
      * *//*
     @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registry) {
    	 registry.setMessageSizeLimit(8192)//设置消息字节参数大小
    	         .setSendBufferSizeLimit(8192)//设置消息缓存大小
    	         .setSendTimeLimit(1000);//设置消息发送时间限制毫秒
    }
    */
      
     
     
}
