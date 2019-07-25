package top.zhengchunxu.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.socket.messaging.SessionConnectEvent;

/**
 * Created by baiguantao on 2017/8/4.
 * STOMP监听类
 * 用于session注册 以及key值获取
 */
public class STOMPConnectEventListener implements ApplicationListener<SessionConnectEvent>{

	@Autowired
	private SocketSessionRegistry socketSessionRegistry;
	
	@Override
	public void onApplicationEvent(SessionConnectEvent event) {
		
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
		String agentId=accessor.getNativeHeader("username").get(0);
		String sessionId=accessor.getSessionId();
		socketSessionRegistry.registerSessionId(agentId, sessionId);
	}

}
