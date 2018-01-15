package com.fdmgroup.finalproject.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.apache.log4j.Logger;

@Aspect
public class LoggingAspect {

	private final static Logger log = Logger
			.getLogger("programmerLogger");
	
	@Around("execution(* com.fdmgroup.finalproject.*.*.*(..))")
	public Object trace(ProceedingJoinPoint pjp ) throws Throwable {
		String methodInfo = pjp.getStaticPart().getSignature().toString();
		log.trace("Entering " + methodInfo);
		try {
			return pjp.proceed();
		} catch (Throwable t) {
			log.error("Exception in " + methodInfo, t);
			throw t;
		} finally {
			log.trace("Exiting " + methodInfo);
		}
	}

}
