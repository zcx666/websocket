package top.zhengchunxu.session;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

import org.springframework.util.Assert;

/**
 * 用户session记录，用于销毁和存储以及获取使用。
 * */
public class SocketSessionRegistry {

	private final ConcurrentMap<String, Set<String>> userSessionIds=new ConcurrentHashMap<>();
	private final Object lock=new Object();
	
	public SocketSessionRegistry() {}

/**
 *获取sessionId 
 * */	
	public Set<String> getSessionIds(String user) {
		Set set=(Set)this.userSessionIds.get(user);
		return set != null?set:Collections.emptySet();
	}
/**
 * 获取所有session
 * */
	public ConcurrentMap<String, Set<String>> getSessionIds(){
		return this.userSessionIds;
	}
	
	

	public void registerSessionId(String user,String sessionId) {
		Assert.notNull(user, "User must not be null");
        Assert.notNull(sessionId, "Session ID must not be null");
        
        Object vv3=this.lock;       
        synchronized (String.class) {
			  Object set=(Set)this.userSessionIds.get(user);
			  if (set == null) {
				  set=new CopyOnWriteArraySet<>();
				  this.userSessionIds.put(user, (Set<String>)set);
			}
			((Set)set).add(sessionId);  
		}
	}
	
	public void unregisterSessionId(String userName, String sessionId) {
		Assert.notNull(userName, "User Name must not be null");
        Assert.notNull(sessionId, "Session ID must not be null");
        
        synchronized (String.class) {
			Set set = (Set)this.userSessionIds.get(userName);
			if (set != null && set.remove(sessionId) && set.isEmpty()) {
				this.userSessionIds.remove(userName);
			}
		}
	}
}
