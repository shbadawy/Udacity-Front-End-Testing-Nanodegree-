package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controller.FileOperations;
import model.InvoiceHeader;
import model.InvoiceItems;

public class InvoiceItemsView extends JFrame implements ActionListener, ListSelectionListener{
	
	private static final long serialVersionUID = 1L;
	private JPanel rightView;
	private JLabel invoiceNumberLabel;
	private JPanel invoiceNumberPanel;
	private static JLabel invoiceNumberValueLabel;
	
	private JPanel invoiceDatePanel;
	private static JTextField invoiceDateTextField;
	private JLabel invoiceDateLabel;

	private JPanel customerNamePanel;
	private static JTextField customerNameTextField;
	private JLabel customerNameLabel;
	
	private JPanel invoiceTotalPanel;
	private JLabel invoiceTotalLabel;
	private static JLabel invoiceTotalValueLabel;

	private static JTable invoiceItemsTable;
	private JPanel invoiceItemPanel;
	private JLabel invoiceItemsLabel;

	private JPanel saveCancelPanel;
	private JPanel createDeleteButtonsPanel;
	private static JButton insertItemButton;
	private static JButton deleteItemButton;
	static JButton saveButton;
	private static JButton cancelButton;
	private static FileOperations fileOp;

	
	public InvoiceItemsView() {
		
		createView();		
		createInvoiceNumber();		
		createInvoiceDate();		
		createCustomerName();		
		createInvoiceTotal();	
		createDeleteInsertButtons();
		createItemsTable();		
		createSaveCancel();
		assignListeners();
		addToView();
		
	}
	
	static void initiatFileOp(FileOperations fileOps) {fileOp = fileOps;}
	
	static void updateItemTable(String invoceNumber, String invoiceDate, String customer, String total, List<InvoiceItems> invoiceItems) {
		
		 invoiceNumberValueLabel.setText(invoceNumber);
		 invoiceDateTextField.setText(invoiceDate);
		 customerNameTextField.setText(customer);
		 invoiceTotalValueLabel.setText(total);
		 
		 fillData(invoiceItems ,  invoceNumber);
		 
		 insertItemButton.setEnabled(true);
		
	}
	
	static void fillData(List<InvoiceItems> invoiceitemList, String invoiceNo) {
		
		int len = invoiceitemList.size();
		InvoiceItems currentInvoice ;
		
		TableModel invoiceItemTableModel = invoiceItemsTable.getModel();
		((DefaultTableModel) invoiceItemTableModel).setRowCount(0);
		
		for (int i = 0 ; i < len; i++) {
			
			currentInvoice = invoiceitemList.get(i);
			if (currentInvoice.getItemNumber().equals(invoiceNo))
				((DefaultTableModel) invoiceItemTableModel).addRow(currentInvoice.getData());
			
		}
		
		cancelButton.setEnabled(true);
		
	}
	
	static void clear() {
		
		clearFields ();
		clearItemTable();
		cancelButton.setEnabled(false);
		deleteItemButton.setEnabled(false);
		invoiceItemsTable.clearSelection();

	}
	
	JPanel getView() {return rightView;}

	
	private void createDeleteInsertButtons() {
		
		createDeleteButtonsPanel = new JPanel();
		createDeleteButtonsPanel.setLayout(new BoxLayout(createDeleteButtonsPanel,0));
		
		deleteItemButton = new JButton("Delete Item");
		insertItemButton = new JButton("Create New Item");
		
		insertItemButton.setEnabled(false);
		deleteItemButton.setEnabled(false);
		
		createDeleteButtonsPanel.add(insertItemButton);
		createDeleteButtonsPanel.add(deleteItemButton);
		
	}
	
	private static void clearFields (){
		
		invoiceNumberValueLabel.setText("");
		invoiceDateTextField.setText("");
		customerNameTextField.setText("");
		invoiceTotalValueLabel.setText("");
		saveButton.setEnabled(false);
		cancelButton.setEnabled(false);
		insertItemButton.setEnabled(false);
		
	}
	
	private static void clearItemTable() {
		
		((DefaultTableModel) invoiceItemsTable.getModel()).getDataVector().removeAllElements();
		invoiceItemsTable.repaint();
		
	}
	
		
	private void addToView() {

		rightView.add(invoiceNumberPanel);
		rightView.add(invoiceDatePanel);
		rightView.add(customerNamePanel);
		rightView.add(invoiceTotalPanel);
		rightView.add(createDeleteButtonsPanel);
		rightView.add(invoiceItemPanel);
		rightView.add(saveCancelPanel);

	}
	
	private void assignListeners() {
		
		saveButton.addActionListener(this);
		cancelButton.addActionListener(this);
		insertItemButton.addActionListener(this);
		deleteItemButton.addActionListener(this);
		invoiceItemsTable.getSelectionModel().addListSelectionListener(this);
		
	}
	
	private void createCustomerName() {
		
		customerNamePanel = new JPanel();
		customerNamePanel.setLayout(new BoxLayout(customerNamePanel,0));
		
		customerNameTextField = new JTextField();
		customerNameLabel = new JLabel("Customer Name"); 
		
		customerNamePanel.add(customerNameLabel);
		customerNamePanel.add(customerNameTextField);
		
	}
	
	private void createInvoiceTotal() {
		
		invoiceTotalPanel = new JPanel();
		invoiceTotalPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		invoiceTotalLabel = new JLabel("Invoice Total");
		invoiceTotalValueLabel = new JLabel();
		
		invoiceTotalPanel.add(invoiceTotalLabel);
		invoiceTotalPanel.add(invoiceTotalValueLabel);
		
	}
	
	private void createView() {
		
		rightView = new JPanel();
		rightView.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		rightView.setLayout(new BoxLayout(rightView,1));
		
	}
	
	private void createInvoiceNumber() {
		
		invoiceNumberPanel = new JPanel();
		invoiceNumberPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		invoiceNumberLabel = new JLabel("Invoice Number");
		invoiceNumberValueLabel = new JLabel();
		
		invoiceNumberPanel.add(invoiceNumberLabel);
		invoiceNumberPanel.add(invoiceNumberValueLabel);
		
	}

	private void createInvoiceDate() {
		
		invoiceDatePanel = new JPanel();
		invoiceDatePanel.setLayout(new BoxLayout(invoiceDatePanel,0));
		
		invoiceDateTextField = new JTextField();
		invoiceDateLabel = new JLabel("Invoice Date");
		
		invoiceDatePanel.add(invoiceDateLabel);
		invoiceDatePanel.add(invoiceDateTextField);
		
	}
	
	private void createItemsTable() {
		
		String[] invoiceItemsTableHeader = {"No.", "Item Name", "Item Price", "Count", "Item Total" };

		DefaultTableModel invoiceItemsTableModel = new DefaultTableModel();
		invoiceItemsTable = new JTable(invoiceItemsTableModel);
		
		for (int i = 0 ; i < invoiceItemsTableHeader.length; i++ )
			invoiceItemsTableModel.addColumn(invoiceItemsTableHeader[i]);
		//	invoiceItemsTableModel.setRowCount(1);
		
		invoiceItemPanel = new JPanel();
		invoiceItemPanel.setLayout(new BoxLayout(invoiceItemPanel,1));
		
		invoiceItemsLabel = new JLabel("Invoice Items");
		invoiceItemPanel.add(invoiceItemsLabel);
		invoiceItemPanel.add(new JScrollPane(invoiceItemsTable));
		
	}
	
	private void createSaveCancel() {
		
		saveCancelPanel = new JPanel();
		saveCancelPanel.setLayout(new BoxLayout(saveCancelPanel,0));
		saveButton = new JButton("Save");
		cancelButton = new JButton("Cancel");
		
		saveButton.setEnabled(false);
		cancelButton.setEnabled(false);
		
		saveCancelPanel.add(saveButton);
		saveCancelPanel.add(cancelButton);
		
	}

	private void checkDate (String inputDate) throws DateTimeParseException{
		
		DateTimeFormatter f = DateTimeFormatter.ofPattern( "dd-MM-yyyy" );
		LocalDate.parse ( inputDate , f );
	
	}
	
	private void deleteItemsWithInvoiceNo(List<InvoiceItems> invoiceDataList, String invNo) {
		
		
		for (int i = 0; i < invoiceDataList.size(); i++) {
			
			InvoiceItems currentHeader = invoiceDataList.get(i);
			if (currentHeader.getItemNumber().equals(invNo)) {
				
				invoiceDataList.remove(i);
				i-=1;
				
			}
			
		}
		
	}
	
	private void saveItemClick() {
		
		try {
			   
			invoiceItemsTable.clearSelection();
			checkDate(invoiceDateTextField.getText());
			List<InvoiceItems> invoiceDataList= fileOp.getInvoiceItemData();
			List<InvoiceHeader> invoiceHeaderDataList = fileOp.getInvoiceData();
			
			// Clear items table 
			deleteItemsWithInvoiceNo(invoiceDataList, invoiceNumberValueLabel.getText() );
	
			// Update items list
			for (int i = 0; i < invoiceItemsTable.getRowCount(); i++) {
				
				invoiceDataList.add(new InvoiceItems((String)invoiceItemsTable.getValueAt(i, 0),
													(String)invoiceItemsTable.getValueAt(i, 1),
													(String)invoiceItemsTable.getValueAt(i, 2), 
													(String)invoiceItemsTable.getValueAt(i, 3)));
				
			}
			
			// Update invoice data
			for (int i = 0 ; i < invoiceHeaderDataList.size(); i++) {
			
				InvoiceHeader currentHeader = invoiceHeaderDataList.get(i);
				if (currentHeader.getInvoiceNo().equals(invoiceNumberValueLabel.getText())) {
					
					currentHeader.setCustomerName(customerNameTextField.getText());
					currentHeader.setInvoiceDate(invoiceDateTextField.getText());
					
				}
					
			}
			
			fileOp.setInvoiceTotals();
			InvoiceHeaderView.updateHeaderTableItems();
			clear();
			saveButton.setEnabled(false);
			cancelButton.setEnabled(false);
		
			
		} catch ( DateTimeParseException e ) {
			
			JOptionPane.showMessageDialog(this, "Wrong date format", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			
		}
		
	}
	
	private void deleteItemClick() {
		
		DefaultTableModel tableModel = (DefaultTableModel) invoiceItemsTable.getModel();
		int rows = invoiceItemsTable.getSelectedRow();
		if (rows >= 0) {
			
			tableModel.removeRow(rows);
			deleteItemButton.setEnabled(false);
			List<InvoiceItems> InvoiceItems = fileOp.getInvoiceItemData();		
			InvoiceItems.remove(rows);
			
			fileOp.setInvoiceTotals();
			InvoiceHeaderView.updateHeaderTableItems();
							
		}
		
	}
	
	private void insertItemClick() {
		
		JLabel itemName = new JLabel("Item name: ");
		JLabel itemPrice = new JLabel("Item price");
		JLabel itemCount = new JLabel("Item count");
		
		JTextField itemNameTextfield = new JTextField();
		JTextField itemPriceTextfield = new JTextField();
		JTextField itemCountTextfield = new JTextField();
		
		JPanel dialogPanel = new JPanel();
		dialogPanel.setLayout(new BoxLayout(dialogPanel, 1));
		JPanel itemDateDialogPanel = new JPanel();
		itemDateDialogPanel.setLayout(new BoxLayout(itemDateDialogPanel, 0));
		JPanel customerNameDialogPanel = new JPanel();
		customerNameDialogPanel.setLayout(new BoxLayout(customerNameDialogPanel, 0));
		
		dialogPanel.add(itemName);
		dialogPanel.add(itemNameTextfield);

		dialogPanel.add(itemPrice);
		dialogPanel.add(itemPriceTextfield);
		
		dialogPanel.add(itemCount);
		dialogPanel.add(itemCountTextfield);
		
		InvoiceItems invoiceItem;
		
		DefaultTableModel tableModel = (DefaultTableModel) invoiceItemsTable.getModel();
			
		int option = JOptionPane.showConfirmDialog(null, dialogPanel, "New Invoice", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			
			invoiceItem = new InvoiceItems(invoiceNumberValueLabel.getText(),
					                       itemNameTextfield.getText(),
					                       itemPriceTextfield.getText(),
					                       itemCountTextfield.getText()
					                        );
			fileOp.addInvoiceItem(invoiceItem);
			tableModel.addRow(invoiceItem.getData());
		    
		    deleteItemButton.setEnabled(true);

			   
		}
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if (arg0.getSource().equals(saveButton)) { saveItemClick();}
		else if (arg0.getSource().equals(cancelButton)) { clear(); }		
		else if (arg0.getSource().equals(deleteItemButton)) {deleteItemClick();}
		else if (arg0.getSource().equals(insertItemButton)) {insertItemClick();}		
		
	} 

	
	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		
		 int row = invoiceItemsTable.getSelectedRow();

		 if (row >= 0) {
			 
			 deleteItemButton.setEnabled(true);
			 saveButton.setEnabled(true);
			 cancelButton.setEnabled(true);
			 
		 }
	
		 
	}
		
	
	
}
