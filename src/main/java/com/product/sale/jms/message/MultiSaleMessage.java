package com.product.sale.jms.message;

/**
 * @author pradeep.a.nair
 * POJO to store details related to Multi sale message type
 */
public class MultiSaleMessage {
	public MultiSaleMessage(String productType, String productValue, String occurance) {
		super();
		this.productType = productType;
		this.productValue = productValue;
		this.occurance = occurance;
	}

	public MultiSaleMessage() {

	}

	@Override
	public String toString() {
		return "MultiSaleMessage [productType=" + productType + ", productValue=" + productValue + ", occurance="
				+ occurance + "]";
	}

	String productType;
	String productValue;
	String occurance;

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductValue() {
		return productValue;
	}

	public void setProductValue(String productValue) {
		this.productValue = productValue;
	}

	public String getOccurance() {
		return occurance;
	}

	public void setOccurance(String occurance) {
		this.occurance = occurance;
	}

}
