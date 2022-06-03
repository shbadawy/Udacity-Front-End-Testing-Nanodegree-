package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import model.InvoiceHeader;
import model.InvoiceItems;
import view.InvoiceHeaderView;

public class FileOperations {
	
	private List<InvoiceHeader> invoiceData;
	private List<InvoiceItems> invoiceItemData;
	private String invoiceDataPath;
	private String invoiceItemDataPath;

	public FileOperations(String csvFilePath) { 
		
		String [] pathList = csvFilePath.split("/");
		int lenOfPathList = pathList.length;
		String fileName = pathList[lenOfPathList-1].split("Header.csv")[0];
		int lenOfFileName = pathList[lenOfPathList-1].length();
		int lenOfFilePath = csvFilePath.length();
		String filesRoot = csvFilePath.substring(0, lenOfFilePath - lenOfFileName);
		invoiceDataPath = csvFilePath;
		invoiceItemDataPath = filesRoot+fileName+"Line.csv";

	}

	public void addInvoice(InvoiceHeader invoiceHeader) {
		
		invoiceData.add(invoiceHeader);
		setInvoiceTotals();
		
	}
	
	public void addInvoiceItem(InvoiceItems invoiceItems) {
		
		invoiceItemData.add(invoiceItems);
		setInvoiceTotals();
		InvoiceHeaderView.updateHeaderTableItems();
		
	}
	

	public List<InvoiceHeader> getInvoiceData() {
		return invoiceData;
	}

	public List<InvoiceItems> getInvoiceItemData() {
		return invoiceItemData;
	}
	
	public void writeData(String fileToSave) throws IOException {
		
		writeInvoicesToCSV(invoiceData, fileToSave + "Header.csv");
		writeInvoiceItemsToCSV(invoiceItemData, fileToSave + "Line.csv");
		
	}
	
	public void readData() {
		
		invoiceData = getInvoiceDataFromCSV(3,invoiceDataPath);
		invoiceItemData = getInvoiceItemDataFromCSV(4,invoiceItemDataPath);
		setInvoiceTotals();
		
	}
	
	
	public void setInvoiceTotals() {
		
		int numOfInvoices = invoiceData.size();
		int numOfInvoicesItems = invoiceItemData.size();
		
		InvoiceHeader invoiceHeader ;
		InvoiceItems invoiceItem;
		
		String invoiceNo;
		String itemInvoiceNo;
		
		Float total;
		
		for (int i = 0; i < numOfInvoices; i++) {
			
			total = (float) 0;
			invoiceHeader = invoiceData.get(i);
			invoiceNo = invoiceHeader.getInvoiceNo();
			for (int j = 0; j < numOfInvoicesItems ; j++) {
				
				invoiceItem = invoiceItemData.get(j);
				
				// Get total price 
			    String itemTotalPrice = invoiceItem.getTotal();
			    
			    // Calculate # items for an invoice
				itemInvoiceNo = invoiceItem.getItemNumber();
				if (itemInvoiceNo.equals(invoiceNo)) total+= Float.parseFloat(itemTotalPrice);
				
			}
			
			invoiceHeader.setTotalAmount(Float.toString(total) );
			
		}
		
	}
		
	private void writeInvoicesToCSV(List<InvoiceHeader> invoiceDataList, String fileName) throws IOException {
		
		Writer writer = null;
        int nRow = invoiceDataList.size() ;
        InvoiceHeader currentInvoice;
        
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "utf-8"));
            
            //write row information
            for (int i = 0 ; i < nRow ; i++){
                 StringBuffer buffer = new StringBuffer();
                 currentInvoice = invoiceDataList.get(i);
                 buffer.append(currentInvoice.getFinalData());
                 writer.write(buffer.toString() + "\r\n");

            }
        } catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally { writer.close(); }
	
	}
	
	private void writeInvoiceItemsToCSV(List<InvoiceItems> invoiceItemDataList, String fileName) throws IOException {
		
		Writer writer = null;
        int nRow = invoiceItemDataList.size() ;
        InvoiceItems currentInvoice;
        
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "utf-8"));
            
            //write row information
            for (int i = 0 ; i < nRow ; i++){
                 StringBuffer buffer = new StringBuffer();
                 currentInvoice = invoiceItemDataList.get(i);
                 buffer.append(currentInvoice.getFinalData());
                 writer.write(buffer.toString() + "\r\n");

            }
        } catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally { writer.close(); }
	
	}
	
	
	private List<InvoiceHeader> getInvoiceDataFromCSV(int numberOfColumn, String path){

		BufferedReader br;
		List<InvoiceHeader> elements = new ArrayList<InvoiceHeader>();
		
		try {
			br = new BufferedReader(new FileReader(new File(path)));
	        String line = null;
	        while((line = br.readLine())!=null) {
	            String[] splitted = line.split(",");
	            elements.add(new InvoiceHeader(splitted[0],splitted[1],splitted[2]));
	        }
	        br.close();
		}catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	    return elements;
	    
	}
	
	private List<InvoiceItems> getInvoiceItemDataFromCSV(int numberOfColumn, String path){

		BufferedReader br;
		List<InvoiceItems> elements = new ArrayList<InvoiceItems>();
		
		try {
			br = new BufferedReader(new FileReader(new File(path)));
	        String line = null;
	        while((line = br.readLine())!=null) {
	            String[] splitted = line.split(",");
	            elements.add(new InvoiceItems(splitted[0],splitted[1],splitted[2],splitted[3]));
	        }
	        br.close();
		}catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	    return elements;
	    
	}

}
