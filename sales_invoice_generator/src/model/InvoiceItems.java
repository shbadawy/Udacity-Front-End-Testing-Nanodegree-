package model;

public class InvoiceItems {
	
	private String itemNumber;
	private String itemName;
	private String price;
	private String count;
	private String total;

	public InvoiceItems(String itemNumber, String itemName, String price, String count) {

		this.itemNumber = itemNumber;
		this.itemName = itemName;
		this.price = price;
		this.count = count;
		
		Float priceN = Float.parseFloat(price);
		Float countN = Float.parseFloat(count);
		
		this.total = Float.toString(priceN * countN);
		
	}
	
	public String getItemNumber() {
		return itemNumber;
	}

	public String getTotal() {
		return total;
	}

	public String[] getData() {
		
		String[] result = {itemNumber, itemName, price, count, total};
		return result;
		
	}
	
	public String getFinalData() { return itemNumber + "," + itemName + "," + price + "," + count; }

}
