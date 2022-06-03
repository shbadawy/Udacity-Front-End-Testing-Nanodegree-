package model;

public class InvoiceHeader {
	
	private String invoiceNo;
	private String invoiceDate;
	private String customerName;
	private String totalAmount;
	
	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public InvoiceHeader(String invoiceNo, String invoiceDate, String customerName) {
		
		this.invoiceNo = invoiceNo;
		this.invoiceDate = invoiceDate;
		this.customerName = customerName;
		
	}
	
	public String[] getData() {
		
		String[] result = {invoiceNo, invoiceDate, customerName,totalAmount};
		return result;
		
	}
	
	public String getFinalData() { return invoiceNo + "," + invoiceDate + "," + customerName; }
	
}
