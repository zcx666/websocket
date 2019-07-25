package top.zhengchunxu.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer{

	//定义线程池
	@Override
	public Executor getAsyncExecutor() {
		//定义线程池
		ThreadPoolTaskExecutor executor=new ThreadPoolTaskExecutor();
		//核心线程池
		executor.setCorePoolSize(10);
		//线程池最大线程数
		executor.setMaxPoolSize(30);
		//线程队列最大线程数
		executor.setQueueCapacity(2000);
		//初始化
		executor.initialize();
		return executor;
	}
}
