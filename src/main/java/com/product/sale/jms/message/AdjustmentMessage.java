package com.product.sale.jms.message;

/**
 * @author pradeep.a.nair
 * POJO to store details related to Adjustment message type
 */
public class AdjustmentMessage {
	public AdjustmentMessage(String productType, String adjustmentType, String adjustmentValue) {
		super();
		this.productType = productType;
		this.adjustmentType = adjustmentType;
		this.adjustmentValue = adjustmentValue;
	}

	public AdjustmentMessage() {

	}

	String productType;

	@Override
	public String toString() {
		return "AdjustmentMessage [productType=" + productType + ", adjustmentType=" + adjustmentType
				+ ", adjustmentValue=" + adjustmentValue + "]";
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getAdjustmentType() {
		return adjustmentType;
	}

	public void setAdjustmentType(String adjustmentType) {
		this.adjustmentType = adjustmentType;
	}

	public String getAdjustmentValue() {
		return adjustmentValue;
	}

	public void setAdjustmentValue(String adjustmentValue) {
		this.adjustmentValue = adjustmentValue;
	}

	String adjustmentType;
	String adjustmentValue;

}
