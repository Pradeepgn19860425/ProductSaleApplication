package com.product.sale.jms.message;

/**
 * @author pradeep.a.nair
 * POJO to store details related to Single sale message type
 */
public class SingleSaleMessage {

	@Override
	public String toString() {
		return "SingleSaleMessage [productType=" + productType + ", productValue=" + productValue + "]";
	}

	public SingleSaleMessage(String productType, String productValue) {
		super();
		this.productType = productType;
		this.productValue = productValue;
	}

	public SingleSaleMessage() {

	}

	String productType;
	String productValue;

	public void setProductValue(String productValue) {
		this.productValue = productValue;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductValue() {
		return productValue;
	}

}
