package com.product.sale.service;

import java.util.concurrent.locks.ReentrantLock;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.config.JmsListenerEndpointRegistry;
import org.springframework.stereotype.Component;

import com.product.sale.data.ProductSaleDataRepository;

/**
 * @author pradeep.a.nair
 * Spring AOP Based class that uses AOP Capabilities to generate sale and adjustment reports
 */
@Aspect
@Component
public class ReportGenerationAspect {

	@Autowired
	ApplicationContext context;

	@Value("${report.count}")
	int reportCount;

	@Value("${process.stop.count}")
	int stopCount;

	static ReentrantLock counterLock = new ReentrantLock(true);
	static int currentCount = 0;

	static void incrementCounter() {
		counterLock.lock();

		try {
			System.out.println(Thread.currentThread().getName() + ": " + currentCount);
			currentCount++;
		} finally {
			counterLock.unlock();
		}
	}

	/**
	 * @param joinPoint
	 *            The method applies spring AOP to generate Sale and Adjustment
	 *            reports for the product sale application
	 */
	@After("execution(* SaleNotificationService.*(..))")
	public void generateReport(JoinPoint joinPoint) {

		System.out.println("Calling Service" + joinPoint.getSignature());
		incrementCounter();
		if (currentCount % reportCount == 0) {
			System.out.println("---------------------Generating Sale Report----------------------------");
			ProductSaleDataRepository.getSaleMap().forEach((k, v) -> {
				System.out.println("Product Type : " + k);
				long totalSale = v.stream().mapToLong(m -> m.getOccurance()).sum();
				long totalValue = v.stream().mapToLong(m -> m.getOccurance() * m.getProductValue()).sum();
				System.out.println("Total Product Value " + totalValue);
				System.out.println("Total sale " + totalSale);
			});

		}

		if (currentCount == stopCount) {
			System.out.println("Need to stop consumption");
			System.out.println("---------------------Generating Adjustment Report----------------------------");
			ProductSaleDataRepository.getAdjustmentMap().forEach((k, v) -> {
				System.out.println("Product Type : " + k);
				v.forEach(item -> System.out.println(item));
			});
			context.getBean(JmsListenerEndpointRegistry.class).stop();

		}

	}

}
