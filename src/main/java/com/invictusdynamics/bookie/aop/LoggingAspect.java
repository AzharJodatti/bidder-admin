package com.invictusdynamics.bookie.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Logging Aspect
 * 
 * @author Amol K Golhar
 * @since 12 April 2018
 *
 */
@Aspect
public class LoggingAspect {

	final static Logger logger = Logger.getLogger(LoggingAspect.class);

	StringBuffer showmessage = null;
	String methodName = null;
	String className = null;
	StringBuffer strBuf = null;

	@Pointcut("execution(* com.invictusdynamics.bookie.controller..*(..))  || execution(* com.invictusdynamics.bookie.dto..*(..)) || execution(* com.invictusdynamics.bookie.entity..*(..))" + "|| execution(* com.invictusdynamics.bookie.service..*(..))  || execution(* com.invictusdynamics.bookie.utility..*(..)) || execution(* com.invictusdynamics.bookie..*(..))")
	public void logPointCut() {
	}

	@Before("logPointCut()")
	public void beforeMethodExecutionAdvice(JoinPoint pjp) {
		showmessage = getMessage(pjp);
		methodName = getMethodName(pjp);
		className = getClassName(pjp);
		if (logger.isInfoEnabled()) {
			strBuf = new StringBuffer("In ").append(className).append(".").append(methodName + " ").append(showmessage);
			logger.info(strBuf.toString());
		}
	}

	@AfterReturning(pointcut = "logPointCut()", returning = "result")
	public void afterMethodExecutionAdvice(JoinPoint pjp, Object result) {
		showmessage = getMessage(pjp);
		methodName = getMethodName(pjp);
		className = getClassName(pjp);
		if (logger.isInfoEnabled()) {
			strBuf = new StringBuffer("Out ").append(className).append(".").append(methodName + " ").append(showmessage);
			logger.info(strBuf);
		}
	}

	@AfterThrowing(pointcut = "logPointCut()", throwing = "error")
	public void catchExceptionAdvice(JoinPoint jp, Throwable error) {
		showmessage = getMessage(jp);
		methodName = getMethodName(jp);
		className = getClassName(jp);
		strBuf = new StringBuffer("Exception block ").append(className).append(".").append(methodName + " ").append(showmessage).append(" - exception ").append(error);
		logger.error(strBuf);

	}

	/**
	 * This method return the cancatinated string of the argument passed in the method
	 * 
	 * @author Amol Golhar
	 * @since 12 April 2018
	 * @param pjp
	 * @return StringBuffer
	 */
	public StringBuffer getMessage(JoinPoint pjp) {
		StringBuffer showMessage = new StringBuffer();
		if (pjp.getArgs() != null && pjp.getArgs().length > 0) {
			for (int i = 0; i < pjp.getArgs().length; i++) {
				if (i == (pjp.getArgs().length - 1)) {
					showMessage.append("Param" + (i + 1)).append(":").append(pjp.getArgs()[i]);
				} else {
					showMessage.append("Param" + (i + 1)).append(":").append(pjp.getArgs()[i]).append(" - ");
				}
			}
		}
		return showMessage;
	}

	/**
	 * This method return the method name
	 * 
	 * @author Amol Golhar
	 * @since 12 April 2018
	 * @param pjp
	 * @return String
	 */
	public String getMethodName(JoinPoint pjp) {
		String methodName = null;
		MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
		methodName = ((Signature) methodSignature).getName();
		return methodName;
	}

	/**
	 * This method return the class name
	 * 
	 * @author Amol Golhar
	 * @since 12 April 2018
	 * @param pjp
	 * @return
	 */
	public String getClassName(JoinPoint pjp) {
		String className = null;
		className = pjp.getTarget().getClass().getName();
		return className;
	}

}
