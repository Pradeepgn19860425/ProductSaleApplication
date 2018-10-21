package com.product.sale.data;

/**
 * @author pradeep.a.nair
 *  POJO to store Sale  records
 */
public class SaleInfo {

	public SaleInfo(long productValue, long occurance) {
		super();
		this.productValue = productValue;
		this.occurance = occurance;
	}
    //assuming product value to be natural  numbers
	long productValue;
	long occurance;

	public SaleInfo() {

	}

	public long getProductValue() {
		return productValue;
	}

	public void setProductValue(long productValue) {
		this.productValue = productValue;
	}

	public long getOccurance() {
		return occurance;
	}

	public void setOccurance(long occurance) {
		this.occurance = occurance;
	}

}
