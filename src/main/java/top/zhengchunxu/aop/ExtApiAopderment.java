package top.zhengchunxu.aop;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestWrapper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import top.zhengchunxu.service.RedisToken;
import top.zhengchunxu.tool.ConstantUtils;
import top.zhengchunxu.tool.ExtApiIdenpotent;

@Aspect
@Component
public class ExtApiAopderment {
	
	@Autowired
	private  RedisToken redisToken;

	@Pointcut("execution(public * top.zhengchunxu.controller.*.(..))")
	public void rlAop() {
		
	}
	
	//环绕通知
	@Around("rlAop()")
	public Object doBefore(ProceedingJoinPoint joinPoint) throws Throwable {
		
		//判断方法上是否加有@ExtApiIdenpotent
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		ExtApiIdenpotent declaredAnnotation = signature.getMethod().getDeclaredAnnotation(ExtApiIdenpotent.class);
		//如果方法上有加ExtApiIdenpotent
		if (declaredAnnotation !=null) {
			String type = declaredAnnotation.type();
			//2.调用接口的时候，将令牌放入请求头中
			String token=null;
			HttpServletRequest request = getRequest();
			if (type.equals(ConstantUtils.EXTAPIHEAD)) {
				token=request.getHeader("token");
			} else {
				token=request.getParameter("token");
			}		
			
			
			if (StringUtils.isEmpty(token)) {
				return "参数错误";
			}
			//3.接口获取相应的令牌，如果能获取该令牌（将当前令牌删除掉），就直接执行该访问的逻辑
			boolean findRedisToken = redisToken.findRedisToken(token);
			//4.接口获取相应的令牌，如果获取不到该令牌，直接返回请勿重复提交
			if (!findRedisToken) {
				response("请勿重复提交");
				return null;
			}
			
		}
		//正常业务逻辑放行
		Object proceed = joinPoint.proceed();
		return proceed;
	}
	
	
	public HttpServletRequest getRequest() {
		ServletRequestAttributes attr= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attr.getRequest();
		return request;
	}
	
	public void response(String msg) throws IOException{
		
		ServletRequestAttributes attributes=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletResponse response = attributes.getResponse();
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		PrintWriter printWriter=response.getWriter();
			
		try {
			printWriter.println(msg);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			printWriter.close();
		}
	}
	}