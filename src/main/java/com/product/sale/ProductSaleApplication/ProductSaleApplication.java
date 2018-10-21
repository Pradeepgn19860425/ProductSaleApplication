package com.product.sale.ProductSaleApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

import com.product.sale.jms.message.AdjustmentMessage;
import com.product.sale.jms.message.MultiSaleMessage;
import com.product.sale.jms.message.SingleSaleMessage;

/**
 * @author pradeep.a.nair Spring boot application class to load Product Sale
 *         Message based Application
 */

@ComponentScan("com.product.sale")
@SpringBootApplication
@EnableAspectJAutoProxy
@EnableJms
public class ProductSaleApplication {

	public static void main(String[] args) throws InterruptedException {

		ConfigurableApplicationContext context = SpringApplication.run(ProductSaleApplication.class, args);

		JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

		// Send a message with a POJO - the template reuse the message converter
		System.out.println("Sending a single sale message.");
		jmsTemplate.convertAndSend("saleQueue", new SingleSaleMessage("apple", "10"), m -> {
			m.setStringProperty("messageType", "singleSaleMessage");
			return m;
		});
        //Adding sleep to simulate a single threaded environment
		Thread.currentThread().sleep(3000);

		System.out.println("Sending a multi sale message.");
		jmsTemplate.convertAndSend("saleQueue", new MultiSaleMessage("apple", "10", "20"), m -> {
			m.setStringProperty("messageType", "multiSaleMessage");
			return m;
		});
        
		//Adding sleep to simulate a single threaded environment
		Thread.currentThread().sleep(3000);
		System.out.println("Sending a adjustment sale message.");
		jmsTemplate.convertAndSend("saleQueue", new AdjustmentMessage("apple", "ADD", "20"), m -> {
			m.setStringProperty("messageType", "adjustmentMessage");
			return m;
		});
		//Adding sleep to simulate a single threaded environment
		Thread.currentThread().sleep(3000);
		System.out.println("Sending a adjustment sale message ");
		jmsTemplate.convertAndSend("saleQueue", new AdjustmentMessage("apple", "MULTIPLY", "20"), m -> {
			m.setStringProperty("messageType", "adjustmentMessage");
			return m;
		});
		//Adding sleep to simulate a single threaded environment
		Thread.currentThread().sleep(3000);
		System.out.println("Sending a adjustment sale message ");
		jmsTemplate.convertAndSend("saleQueue", new AdjustmentMessage("apple", "SUBTRACT", "20"), m -> {
			m.setStringProperty("messageType", "adjustmentMessage");
			return m;
		});
		//Adding sleep to simulate a single threaded environment
		Thread.currentThread().sleep(3000);
		System.out.println("Sending a adjustment sale message ");
		jmsTemplate.convertAndSend("saleQueue", new AdjustmentMessage("apple", "SUBTRACT", "20"), m -> {
			m.setStringProperty("messageType", "adjustmentMessage");
			return m;
		});

	}

}
