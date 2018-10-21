package com.product.sale.data;

/**
 * @author pradeep.a.nair
 * POJO to store Sale Adjustment records
 */
public class SaleAdjustmentInfo {

	public enum AdjustmentType{ADD,SUBTRACT,MULTIPLY};
	//assuming adjustment values to be natural number
	private long adjustmentValue;
	private AdjustmentType adjustmentType;
	

	public SaleAdjustmentInfo(AdjustmentType adjustmentType, long adjustmentValue) throws Exception {
		super();
		this.adjustmentType = adjustmentType;
		this.adjustmentValue = adjustmentValue;
	}

	@Override
	public String toString() {
		return "SaleAdjustment [adjustmentType=" + adjustmentType + ", adjustmentValue=" + adjustmentValue + "]";
	}

	public SaleAdjustmentInfo() {

	}

	public AdjustmentType getAdjustmentType() {
		return adjustmentType;
	}

	public void setAdjustmentType(AdjustmentType adjustmentType) {
		this.adjustmentType = adjustmentType;
	}

	public long getAdjustmentValue() {
		return adjustmentValue;
	}

	public void setAdjustmentValue(long adjustmentValue) throws Exception {
				this.adjustmentValue = adjustmentValue;
	}

}
