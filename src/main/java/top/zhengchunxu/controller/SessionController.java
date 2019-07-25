package top.zhengchunxu.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import top.zhengchunxu.pojo.OutMessage;
import top.zhengchunxu.session.SocketSessionRegistry;

@Controller
public class SessionController {

	@Autowired
	private SocketSessionRegistry socketSessionRegistry;	
	@Autowired
	private SimpMessagingTemplate template;
	
	   @RequestMapping(value = "/index")
	    public  String index(){
	        return "/index";
	    }
	    @RequestMapping(value = "/msg/message")
	    public  String ToMessage(){
	        return "/message";
	    }
	    @RequestMapping(value = "/msg/messaget2")
	    public  String ToMessaget2(){
	        return "/messaget2";
	    }
	    /**
	     * 用户广播
	     * 发送消息广播  用于内部发送使用
	     * @param request
	     * @return
	     */
	    @MessageMapping("/msg/sendcommuser")
	    public void SendToCommUserMessage(HttpServletRequest request,OutMessage outMessage) {
	    	
	    	 List<String> keys=socketSessionRegistry.getSessionIds().entrySet()
	                 .stream().map(Map.Entry::getKey)
	                 .collect(Collectors.toList());
	         Date date=new Date();
	         keys.forEach(x->{
	             String sessionId=socketSessionRegistry.getSessionIds(x).stream().findFirst().get().toString();
	             System.err.println("收到的信息是==="+sessionId);
	             template.convertAndSendToUser(sessionId,"/topic/greetings",new OutMessage("commmsg：allsend, " + "send  comm" +date.getTime()+ "!"),createHeaders(sessionId));

	         });
          
	    	
	    }
	    
	    private MessageHeaders createHeaders(String sessionId) {
	        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
	        headerAccessor.setSessionId(sessionId);
	        headerAccessor.setLeaveMutable(true);
	        return headerAccessor.getMessageHeaders();
	    }
	   
}
