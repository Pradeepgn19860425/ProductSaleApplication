package com.product.sale.data;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author pradeep.a.nair
 * This class acts as a repository to store Sale and Adjustment data for different product type
 */
public class ProductSaleDataRepository {

	private static final ConcurrentHashMap<String, List<SaleInfo>> saleMap = new ConcurrentHashMap<String, List<SaleInfo>>();
	private static final ConcurrentHashMap<String, List<SaleAdjustmentInfo>> adjustmentMap = new ConcurrentHashMap<String, List<SaleAdjustmentInfo>>();
	public static ConcurrentHashMap<String, List<SaleInfo>> getSaleMap() {
		return saleMap;
	}
	public static ConcurrentHashMap<String, List<SaleAdjustmentInfo>> getAdjustmentMap() {
		return adjustmentMap;
	}

}
