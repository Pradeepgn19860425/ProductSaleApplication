package com.product.sale.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.product.sale.data.ProductSaleDataRepository;
import com.product.sale.data.SaleAdjustmentInfo;
import com.product.sale.data.SaleInfo;
import com.product.sale.exception.InvalidDataException;
import com.product.sale.jms.message.AdjustmentMessage;
import com.product.sale.jms.message.MultiSaleMessage;
import com.product.sale.jms.message.SingleSaleMessage;
import com.product.sale.util.NotificationMessageMapperUtil;

/**
 * @author pradeep.a.nair
 * Service class that provides different operations to process different type of notification messages
 */
@Service
public class SaleNotificationService {

	static ReentrantLock accessLock = new ReentrantLock(true);
    
	/**
	 * @param singleSaleMessage
	 * @throws InvalidDataException
	 * This method will process Single Sale Messages and add the relevant data in ProductSaleDataRepository
	 */
	public void processSingleSaleMessage(SingleSaleMessage singleSaleMessage) throws InvalidDataException {
		SaleInfo saleInfo = NotificationMessageMapperUtil.mapToSaleInfo(singleSaleMessage);
		recordSaleMessage(saleInfo, singleSaleMessage.getProductType());
	}
	
	

	/**
	 * @param multiSaleMessage
	 * @throws InvalidDataException
	 * This method will process Multi Sale Messages and add the relevant data in ProductSaleDataRepository
	 */
	public void processMultiSaleMessage(MultiSaleMessage multiSaleMessage) throws InvalidDataException {
		SaleInfo saleInfo = NotificationMessageMapperUtil.mapToSaleInfo(multiSaleMessage);
		recordSaleMessage(saleInfo, multiSaleMessage.getProductType());

	}

	/**
	 * @param adjustmentMessage
	 * @throws NumberFormatException
	 * @throws Exception
	 * This method will process Adjustment Messages and add/update the relevant data in ProductSaleDataRepository
	 */
	public void processAdjustmentMessage(AdjustmentMessage adjustmentMessage) throws NumberFormatException, Exception {

		SaleAdjustmentInfo saleAdjustmentInfo = NotificationMessageMapperUtil.mapToAdjustmentInfo(adjustmentMessage);
		recordAdjustmentMessage(saleAdjustmentInfo, adjustmentMessage.getProductType());

	}

	/**
	 * @param saleAdjustmentInfo
	 * @param productType
	 * This method adds adjustment data in ProductSaleDataRepository
	 */
	private void recordAdjustmentMessage(SaleAdjustmentInfo saleAdjustmentInfo, String productType) {
		accessLock.lock();
		try {
			List<SaleAdjustmentInfo> adjustmentInfoList = ProductSaleDataRepository.getAdjustmentMap().get(productType);
			if (CollectionUtils.isEmpty(adjustmentInfoList)) {
				List<SaleAdjustmentInfo> inputadjustmentInfoList = new ArrayList<SaleAdjustmentInfo>();
				inputadjustmentInfoList.add(saleAdjustmentInfo);
				ProductSaleDataRepository.getAdjustmentMap().put(productType, inputadjustmentInfoList);
				updateSaleMapwithAdjustment(saleAdjustmentInfo, productType);

			} else {
				adjustmentInfoList.add(saleAdjustmentInfo);
				updateSaleMapwithAdjustment(saleAdjustmentInfo, productType);
			}
		}

		finally {
			accessLock.unlock();
		}

	}

	/**
	 * @param saleInfo
	 * @param productType
	 * This method adds sale data in ProductSaleDataRepository
	 */
	private void recordSaleMessage(SaleInfo saleInfo, String productType) {
		accessLock.lock();
		try {
			List<SaleInfo> saleInfoList = ProductSaleDataRepository.getSaleMap().get(productType);
			if (CollectionUtils.isEmpty(saleInfoList)) {
				List<SaleInfo> inputSaleInfoList = new ArrayList<SaleInfo>();
				inputSaleInfoList.add(saleInfo);
				ProductSaleDataRepository.getSaleMap().put(productType, inputSaleInfoList);
			} else {
				saleInfoList.add(saleInfo);
			}
		} finally {
			accessLock.unlock();
		}
	}

	/**
	 * @param saleAdjustmentInfo
	 * @param productType
	 * This method updates the already existing sale records for each adjustment received
	 */
	private void updateSaleMapwithAdjustment(SaleAdjustmentInfo saleAdjustmentInfo, String productType) {
		ProductSaleDataRepository.getSaleMap().entrySet().stream().filter(x -> productType.equals(x.getKey()))
				.forEach(v -> v.getValue().stream().forEach(x -> applyAdjustment(x,
						saleAdjustmentInfo.getAdjustmentType(), saleAdjustmentInfo.getAdjustmentValue())));
	}

	/**
	 * @param saleInfo
	 * @param adjustmentType
	 * @param adjustmnetValue
	 * This method has logic for applying adjustment depending on the adjustment type
	 */
	private void applyAdjustment(SaleInfo saleInfo, SaleAdjustmentInfo.AdjustmentType adjustmentType,
			long adjustmnetValue) {

		switch (adjustmentType) {
		case ADD:

			long addedValue = saleInfo.getProductValue() + adjustmnetValue;
			saleInfo.setProductValue(addedValue);
			break;

		case SUBTRACT:

			long subValue = saleInfo.getProductValue() - adjustmnetValue;
			saleInfo.setProductValue(subValue);
			break;

		case MULTIPLY:

			long mulValue = saleInfo.getProductValue() * adjustmnetValue;
			saleInfo.setProductValue(mulValue);
			break;

		}

	}

}
