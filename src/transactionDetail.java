public class transactionDetail extends Main {
	private String transactionID;
	private String productID;
	private Integer qty;
	
	public transactionDetail(String transactionID, String productID, Integer qty) {
		super();
		this.transactionID = transactionID;
		this.productID = productID;
		this.qty = qty;
	}

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}
	
	
}
