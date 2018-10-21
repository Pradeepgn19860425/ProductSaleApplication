package com.product.sale.ProductSaleApplication;

import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import com.product.sale.data.ProductSaleDataRepository;
import com.product.sale.data.SaleAdjustmentInfo;
import com.product.sale.exception.InvalidDataException;
import com.product.sale.jms.message.AdjustmentMessage;
import com.product.sale.jms.message.MultiSaleMessage;
import com.product.sale.jms.message.SingleSaleMessage;
import com.product.sale.service.SaleNotificationService;

@RunWith(SpringRunner.class)

public class SaleNotificationServiceTest {

	@Configuration
	static class SaleNotificationServiceContextConfiguration {
		@Bean
		SaleNotificationService getSaleNotificationService() {
			return new SaleNotificationService();
		}

	}

	@Autowired
	SaleNotificationService saleNotificationService;

	@SuppressWarnings("static-access")
	@Test
	public void testNotificationeMessage1ForSingleSale() throws InvalidDataException {
		SingleSaleMessage saleMessage = new SingleSaleMessage("apple", "10");
		saleNotificationService.processSingleSaleMessage(saleMessage);
		assert (ProductSaleDataRepository.getSaleMap().get("apple").stream()
				.filter(x -> (x.getOccurance() == 1 && x.getProductValue() == 10)).collect(Collectors.toList())
				.size() == 1);

	}

	@SuppressWarnings("static-access")
	@Test
	public void testNotificationeMessage2ForMultiSaleMessage() throws InvalidDataException {
		MultiSaleMessage saleMessage = new MultiSaleMessage("apple", "10", "2");
		saleNotificationService.processMultiSaleMessage(saleMessage);
		assert (ProductSaleDataRepository.getSaleMap().get("apple").stream()
				.filter(x -> (x.getOccurance() == 2 && x.getProductValue() == 10)).collect(Collectors.toList())
				.size() == 1);

	}

	@SuppressWarnings("static-access")
	@Test
	public void testNotificationeMessage3ForAdjustmentSaleMessage() throws NumberFormatException, Exception {
		AdjustmentMessage saleMessage = new AdjustmentMessage("apple", "ADD", "10");
		saleNotificationService.processAdjustmentMessage(saleMessage);
		assert (ProductSaleDataRepository.getAdjustmentMap().get("apple").stream()
				.filter(x -> x.getAdjustmentType().equals(SaleAdjustmentInfo.AdjustmentType.ADD)
						&& x.getAdjustmentValue() == 10)
				.collect(Collectors.toList()).size() == 1);

	}

	@SuppressWarnings("static-access")
	@Test
	public void testNotificationeMessage4ForAdjustmentSaleMessage() throws NumberFormatException, Exception {
		AdjustmentMessage saleMessage = new AdjustmentMessage("apple", "SUBTRACT", "10");
		saleNotificationService.processAdjustmentMessage(saleMessage);
		assert (ProductSaleDataRepository.getAdjustmentMap().get("apple").stream()
				.filter(x -> x.getAdjustmentType().equals(SaleAdjustmentInfo.AdjustmentType.SUBTRACT)
						&& x.getAdjustmentValue() == 10)
				.collect(Collectors.toList()).size() == 1);

	}

	@SuppressWarnings("static-access")
	@Test
	public void testNotificationeMessage5ForAdjustmentSaleMessage() throws NumberFormatException, Exception {
		AdjustmentMessage saleMessage = new AdjustmentMessage("apple", "MULTIPLY", "10");
		saleNotificationService.processAdjustmentMessage(saleMessage);
		assert (ProductSaleDataRepository.getAdjustmentMap().get("apple").stream()
				.filter(x -> x.getAdjustmentType().equals(SaleAdjustmentInfo.AdjustmentType.MULTIPLY)
						&& x.getAdjustmentValue() == 10)
				.collect(Collectors.toList()).size() == 1);

	}

	@SuppressWarnings("static-access")
	@Test(expected = InvalidDataException.class)
	public void testNotificationeMessage6ForAdjustmentSaleMessage() throws NumberFormatException, Exception {
		AdjustmentMessage saleMessage = new AdjustmentMessage("apple", "DIVIDE", "10");
		saleNotificationService.processAdjustmentMessage(saleMessage);

	}

	@SuppressWarnings("static-access")
	@Test(expected = InvalidDataException.class)
	public void testNotificationeMessage7ForAdjustmentSaleMessage() throws NumberFormatException, Exception {
		AdjustmentMessage saleMessage = new AdjustmentMessage("apple", "MULTIPLY", "0");
		saleNotificationService.processAdjustmentMessage(saleMessage);

	}

	@SuppressWarnings("static-access")
	@Test(expected = InvalidDataException.class)
	public void testNotificationeMessage8ForAdjustmentSaleMessage() throws NumberFormatException, Exception {
		AdjustmentMessage saleMessage = new AdjustmentMessage("apple", "MULTIPLY", "-10");
		saleNotificationService.processAdjustmentMessage(saleMessage);

	}

	@SuppressWarnings("static-access")
	@Test(expected = InvalidDataException.class)
	public void testNotificationeMessage9ForSingleSale() throws InvalidDataException {
		SingleSaleMessage saleMessage = new SingleSaleMessage("apple", "0");
		saleNotificationService.processSingleSaleMessage(saleMessage);

	}

	@SuppressWarnings("static-access")
	@Test(expected = InvalidDataException.class)
	public void testNotificationeMessage10ForSingleSale() throws InvalidDataException {
		SingleSaleMessage saleMessage = new SingleSaleMessage("apple", "-10");
		saleNotificationService.processSingleSaleMessage(saleMessage);

	}

	@SuppressWarnings("static-access")
	@Test(expected = InvalidDataException.class)
	public void testNotificationeMessage11ForMultiSaleMessage() throws InvalidDataException {
		MultiSaleMessage saleMessage = new MultiSaleMessage("apple", "0", "2");
		saleNotificationService.processMultiSaleMessage(saleMessage);

	}

	@SuppressWarnings("static-access")
	@Test(expected = InvalidDataException.class)
	public void testNotificationeMessage12ForMultiSaleMessage() throws InvalidDataException {
		MultiSaleMessage saleMessage = new MultiSaleMessage("apple", "-10", "2");
		saleNotificationService.processMultiSaleMessage(saleMessage);

	}

	@SuppressWarnings("static-access")
	@Test(expected = InvalidDataException.class)
	public void testNotificationeMessage13ForMultiSaleMessage() throws InvalidDataException {
		MultiSaleMessage saleMessage = new MultiSaleMessage("apple", "10", "0");
		saleNotificationService.processMultiSaleMessage(saleMessage);

	}

	@SuppressWarnings("static-access")
	@Test(expected = InvalidDataException.class)
	public void testNotificationeMessage14ForMultiSaleMessage() throws InvalidDataException {
		MultiSaleMessage saleMessage = new MultiSaleMessage("apple", "10", "-2");
		saleNotificationService.processMultiSaleMessage(saleMessage);

	}

	@SuppressWarnings("static-access")
	@Test(expected = InvalidDataException.class)
	public void testNotificationeMessage15ForAdjustmentSaleMessage() throws NumberFormatException, Exception {
		AdjustmentMessage saleMessage = new AdjustmentMessage("", "ADD", "10");
		saleNotificationService.processAdjustmentMessage(saleMessage);

	}

	@SuppressWarnings("static-access")
	@Test(expected = InvalidDataException.class)
	public void testNotificationeMessage16ForAdjustmentSaleMessage() throws NumberFormatException, Exception {
		AdjustmentMessage saleMessage = new AdjustmentMessage(null, "ADD", "10");
		saleNotificationService.processAdjustmentMessage(saleMessage);

	}

	@SuppressWarnings("static-access")
	@Test(expected = InvalidDataException.class)
	public void testNotificationeMessage17ForMultiSaleMessage() throws InvalidDataException {
		MultiSaleMessage saleMessage = new MultiSaleMessage("", "10", "2");
		saleNotificationService.processMultiSaleMessage(saleMessage);

	}

	@SuppressWarnings("static-access")
	@Test(expected = InvalidDataException.class)
	public void testNotificationeMessage18ForMultiSaleMessage() throws InvalidDataException {
		MultiSaleMessage saleMessage = new MultiSaleMessage(null, "10", "2");
		saleNotificationService.processMultiSaleMessage(saleMessage);

	}

	@SuppressWarnings("static-access")
	@Test(expected = InvalidDataException.class)
	public void testNotificationeMessage19ForSingleSale() throws InvalidDataException {
		SingleSaleMessage saleMessage = new SingleSaleMessage("", "10");
		saleNotificationService.processSingleSaleMessage(saleMessage);

	}

	@SuppressWarnings("static-access")
	@Test(expected = InvalidDataException.class)
	public void testNotificationeMessage20ForSingleSale() throws InvalidDataException {
		SingleSaleMessage saleMessage = new SingleSaleMessage(null, "10");
		saleNotificationService.processSingleSaleMessage(saleMessage);

	}

}
