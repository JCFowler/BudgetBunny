package com.revature.aspect;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect
{
	private Logger log = Logger.getRootLogger();
	
	@Around("everything()")
	public Object logger(ProceedingJoinPoint pjp)
	{
		Object obj = null;
		
		Signature sig = pjp.getSignature();
		Object[] args = pjp.getArgs();
		
		log.warn("Method with signature "+sig+" called.");
		log.warn("With arguments: "+Arrays.toString(args));
		
		try{
			obj = pjp.proceed();
		}catch(Throwable t)
		{
			log.error("Method threw "+t.getMessage());
			log.warn(Arrays.toString(t.getStackTrace()));
		}
		log.warn(sig+" returned: "+obj);
		return obj;
	}
	
	
	//hook for everything
	@Pointcut("execution(* com.revature..*(..))")
	public void everything(){}
}
