public class Cart {
	private String productID;
	private String productName;
    private Integer productPrice;
    private Integer quantity;
	
	public Cart(String productID, String productName, Integer productPrice, Integer quantity) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.productPrice = productPrice;
		this.quantity = quantity;
	}
	
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
    
	 @Override
	 public String toString() {
	        return quantity + "x " + " "+
	                productName + " " +
	                "(" + "Rp." + productPrice + ")";
	    }

	public int getPrice() {
		return productPrice.intValue();
	}
	}

