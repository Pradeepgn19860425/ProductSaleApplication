package com.product.sale.service;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author pradeep.a.nair
 * Spring AOP Based class that uses AOP Capabilities to print exceptions to the console
 */
@Aspect
@Component
public class ExceptionLoggerAspect {
	
	 /**
	 * @param exeption
	 * This method uses spring AOP to print exceptions to the console
	 */
	@AfterThrowing(pointcut = "execution(* SaleNotificationService.*(..))", throwing = "ex")
	    public void logException(Exception ex) {
	     //ideally would log the same instead of printing on console  
		 ex.printStackTrace();
	    }

}
