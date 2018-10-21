package com.product.sale.jms.receiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.product.sale.exception.InvalidDataException;
import com.product.sale.jms.message.AdjustmentMessage;
import com.product.sale.jms.message.MultiSaleMessage;
import com.product.sale.jms.message.SingleSaleMessage;
import com.product.sale.service.SaleNotificationService;

/**
 * @author pradeep.a.nair
 * Provides the capability to listen and filter different type of messages based on their message type
 */
@Component
public class SaleNotificationMessageReceiver {
	@Autowired
	SaleNotificationService saleNotificationService;
	
	/**
	 * @param message
	 * @throws InvalidDataException
	 * This method filters Single Sale Message based on message type for further processing
	 */
	@JmsListener(destination = "saleQueue", selector = "messageType = 'singleSaleMessage'")
    public void receiveMessage(SingleSaleMessage message) throws InvalidDataException {
        System.out.println("Received SingleSale Message<" + message + ">");
        saleNotificationService.processSingleSaleMessage(message);
    }
	
	/**
	 * @param message
	 * @throws InvalidDataException
	 * This method filters Multi Sale Message based on message type for further processing
	 */
	@JmsListener(destination = "saleQueue", selector = "messageType = 'multiSaleMessage'")
    public void receiveMessage(MultiSaleMessage message) throws InvalidDataException {
        System.out.println("Received MultiSale Message<" + message + ">");
        saleNotificationService.processMultiSaleMessage(message);
    }

	/**
	 * @param message
	 * @throws NumberFormatException
	 * @throws Exception
	 * This method filters Adjustment Sale Message based on message type for further processing
	 */
	@JmsListener(destination = "saleQueue", selector = "messageType = 'adjustmentMessage'")
    public void receiveMessage(AdjustmentMessage message) throws NumberFormatException, Exception {
        System.out.println("Received Adjustment Message<" + message + ">");
        saleNotificationService.processAdjustmentMessage(message);
    }

}
