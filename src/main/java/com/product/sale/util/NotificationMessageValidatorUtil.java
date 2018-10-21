package com.product.sale.util;

import org.springframework.util.StringUtils;

import com.product.sale.constants.Constants;
import com.product.sale.data.SaleAdjustmentInfo;
import com.product.sale.exception.InvalidDataException;
import com.product.sale.jms.message.AdjustmentMessage;
import com.product.sale.jms.message.MultiSaleMessage;
import com.product.sale.jms.message.SingleSaleMessage;

/**
 * @author pradeep.a.nair
 * Utility class to validate the content of different types of Notification messages
 *
 */
public class NotificationMessageValidatorUtil {

	/**
	 * @param singleSaleMessage
	 * @throws InvalidDataException
	 * This method validates content of Single sale message 
	 */
	public static void validateSingleSaleMessage(SingleSaleMessage singleSaleMessage) throws InvalidDataException {
		if (Long.valueOf(singleSaleMessage.getProductValue()) <= 0)
			throw new InvalidDataException(String.format(Constants.MESSAGE_ZERO_NEGATIVE_VALIDATION_ERROR,
					Constants.SINGLE_SALE_MESSAGE, Constants.PRODUCT_VALUE));
		else if (StringUtils.isEmpty(singleSaleMessage.getProductType()))
			throw new InvalidDataException(String.format(Constants.MESSAGE_INVALID_PRODUCT_TYPE_ERROR,
					Constants.SINGLE_SALE_MESSAGE, Constants.PRODUCT_TYPE));
	}

	/**
	 * @param multiSaleMessage
	 * @throws InvalidDataException
	 * This method validates content of Multi sale message
	 */
	public static void validateMultiSaleMessage(MultiSaleMessage multiSaleMessage) throws InvalidDataException {
		if (Long.valueOf(multiSaleMessage.getProductValue()) <= 0)
			throw new InvalidDataException(String.format(Constants.MESSAGE_ZERO_NEGATIVE_VALIDATION_ERROR,
					Constants.MULTI_SALE_MESSAGE, Constants.PRODUCT_VALUE));
		else if (Long.valueOf(multiSaleMessage.getOccurance()) <= 0)
			throw new InvalidDataException(String.format(Constants.MESSAGE_ZERO_NEGATIVE_VALIDATION_ERROR,
					Constants.MULTI_SALE_MESSAGE, Constants.PRODUCT_OCCURANCE));
		else if (StringUtils.isEmpty(multiSaleMessage.getProductType()))
			throw new InvalidDataException(String.format(Constants.MESSAGE_INVALID_PRODUCT_TYPE_ERROR,
					Constants.MULTI_SALE_MESSAGE, Constants.PRODUCT_TYPE));

	}

	/**
	 * @param adjustmentMessage
	 * @throws InvalidDataException
	 * This method validates content of Adjustment sale message
	 */
	public static void validateAdjustmentSaleMessage(AdjustmentMessage adjustmentMessage) throws InvalidDataException {
		if (Long.valueOf(adjustmentMessage.getAdjustmentValue()) <= 0)
			throw new InvalidDataException(String.format(Constants.MESSAGE_ZERO_NEGATIVE_VALIDATION_ERROR,
					Constants.ADJUSTMENT_MESSAGE, Constants.ADJUSTMENT_VALUE));
		else if (!isValidAdjustmentType(adjustmentMessage.getAdjustmentType()))
			throw new InvalidDataException(String.format(Constants.MESSAGE_INVALID_ADJUSTMENT_TYPE_ERROR,
					Constants.ADJUSTMENT_MESSAGE, Constants.ADJUSTMENT_TYPE));
		else if (StringUtils.isEmpty(adjustmentMessage.getProductType()))
			throw new InvalidDataException(String.format(Constants.MESSAGE_INVALID_PRODUCT_TYPE_ERROR,
					Constants.ADJUSTMENT_MESSAGE, Constants.PRODUCT_TYPE));

	}

	/**
	 * @param adjustmentType
	 * @return boolean
	 * This method validates input string against AdjustmentType enum
	 */
	public static boolean isValidAdjustmentType(String adjustmentType) {

		for (SaleAdjustmentInfo.AdjustmentType c : (SaleAdjustmentInfo.AdjustmentType.values())) {
			if (c.name().equals(adjustmentType)) {
				return true;
			}
		}

		return false;
	}

}
