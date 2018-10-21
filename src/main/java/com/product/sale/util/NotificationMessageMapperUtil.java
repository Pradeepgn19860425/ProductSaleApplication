package com.product.sale.util;

import com.product.sale.data.SaleAdjustmentInfo;
import com.product.sale.data.SaleInfo;
import com.product.sale.exception.InvalidDataException;
import com.product.sale.jms.message.AdjustmentMessage;
import com.product.sale.jms.message.MultiSaleMessage;
import com.product.sale.jms.message.SingleSaleMessage;

/**
 * @author pradeep.a.nair
 * Utility Mapper class for different type of object conversions
 */
public class NotificationMessageMapperUtil {

	/**
	 * @param singleSaleMessage
	 * @return SaleInfo
	 * @throws InvalidDataException
	 * Mapper method that converts incoming singleSale Message to SaleInfo object for further processing 
	 */
	public static SaleInfo mapToSaleInfo(SingleSaleMessage singleSaleMessage) throws InvalidDataException {

		NotificationMessageValidatorUtil.validateSingleSaleMessage(singleSaleMessage);
		return new SaleInfo(Long.valueOf(singleSaleMessage.getProductValue()), 1);

	}
	
	/**
	 * @param multiSaleMessage
	 * @return SaleInfo
	 * @throws InvalidDataException
	 * Mapper  method that converts incoming MultiSale Message to SaleInfo object for further processing 
	 */
	public static SaleInfo mapToSaleInfo(MultiSaleMessage multiSaleMessage) throws InvalidDataException {

		NotificationMessageValidatorUtil.validateMultiSaleMessage(multiSaleMessage);
		return new SaleInfo(Long.valueOf(multiSaleMessage.getProductValue()),Long.valueOf(multiSaleMessage.getOccurance()));

	}
	
	/**
	 * @param adjustmentMessage
	 * @returnSaleAdjustmentInfo
	 * @throws NumberFormatException
	 * @throws Exception
	 * Mapper method that converts incoming Adjustment Message to AdjustmentSaleInfo object for further processing
	 */
	public static SaleAdjustmentInfo mapToAdjustmentInfo(AdjustmentMessage adjustmentMessage) throws NumberFormatException, Exception {

		NotificationMessageValidatorUtil.validateAdjustmentSaleMessage(adjustmentMessage);
		return new SaleAdjustmentInfo(SaleAdjustmentInfo.AdjustmentType.valueOf(adjustmentMessage.getAdjustmentType()),Long.valueOf(adjustmentMessage.getAdjustmentValue()));

	}

}
