package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controller.FileOperations;
import model.InvoiceHeader;
import model.InvoiceItems;

public class InvoiceHeaderView extends JFrame implements ListSelectionListener, ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel leftView;
	private JPanel createDeleteButtonsPanel;

	private JButton loadFileButton;
	private JButton saveFileButton;
	
	private JLabel tableLable;
	private static JTable invoiceTable;

	private JButton insertInvoiceButton;
	private JButton deleteInvoiceButton;
	
	private static FileOperations fileOp;
	
	public InvoiceHeaderView() {
		
		createLeftView();
		createSaveLoadFileButtons();
		createHeaderTable();
		createDeleteInsertButtons();
		assignListeners();
		addToView();
		
	}
	
	JPanel getView() { return leftView;}
	
	public static void updateHeaderTableItems() {fillData(fileOp.getInvoiceData());}
	
	void insertInvoiceClick() {
		
		JLabel invoiceDate = new JLabel("Invoice date: ");
		JLabel customerName = new JLabel("Customer name");
		
		JTextField invoiceTextfield = new JTextField();
		JTextField customerNameTextfield = new JTextField();
		
		JPanel dialogPanel = new JPanel();
		dialogPanel.setLayout(new BoxLayout(dialogPanel, 1));
		JPanel invoiceDateDialogPanel = new JPanel();
		invoiceDateDialogPanel.setLayout(new BoxLayout(invoiceDateDialogPanel, 0));
		JPanel customerNameDialogPanel = new JPanel();
		customerNameDialogPanel.setLayout(new BoxLayout(customerNameDialogPanel, 0));
		
		dialogPanel.add(invoiceDate);
		dialogPanel.add(invoiceTextfield);

		dialogPanel.add(customerName);
		dialogPanel.add(customerNameTextfield);
		
		InvoiceHeader invoiceHeader;
		DefaultTableModel tableModel = (DefaultTableModel) invoiceTable.getModel();
		int newInvoiceNomber = tableModel.getRowCount()+1;
			
		int option = JOptionPane.showConfirmDialog(null, dialogPanel, "New Invoice", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
		
			try {
					
				checkDate(invoiceTextfield.getText());
				invoiceHeader = new InvoiceHeader(Integer.toString(newInvoiceNomber), 
						                          invoiceTextfield.getText(),
						                          customerNameTextfield.getText());
				
				fileOp.addInvoice(invoiceHeader);
			    tableModel.addRow(invoiceHeader.getData());
			    deleteInvoiceButton.setEnabled(true);
				saveFileButton.setEnabled(true);

			    
			}catch ( DateTimeParseException e ) {
				
					JOptionPane.showMessageDialog(this, "Wrong date format", "Error", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
					
			}
		
		} 
		
	}
	
	private void assignListeners() {
		
		saveFileButton.addActionListener(this);
		loadFileButton.addActionListener(this);
		deleteInvoiceButton.addActionListener(this);
		insertInvoiceButton.addActionListener(this);
		
		invoiceTable.getSelectionModel().addListSelectionListener(this);
		
	}
			
	private void addToView() {
			
			leftView.add(saveFileButton);
			leftView.add(loadFileButton);
			leftView.add(tableLable);
			leftView.add(new JScrollPane(invoiceTable));
			leftView.add(createDeleteButtonsPanel);
			
		}
		
	private void createLeftView() {
			
			leftView = new JPanel();
			leftView.setLayout(new BoxLayout(leftView, 1));
			leftView.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			
		}
		
	private void createSaveLoadFileButtons () {
	
		saveFileButton = new JButton("Save File");
		loadFileButton = new JButton("Load File");
		saveFileButton.setEnabled(false);
		
	}
	
	private void createHeaderTable() {
		
		String [] invoiceTableHeader = {"Invoice No.", "Invoice Date", "Customer Name", "Total amount" };
		
		tableLable = new JLabel("Invoices Table");
		DefaultTableModel tableModel = new DefaultTableModel();
		invoiceTable = new JTable(tableModel);
		invoiceTable.setDefaultEditor(Object.class, null);
		
		for (int i = 0 ; i < invoiceTableHeader.length; i++ )
			tableModel.addColumn(invoiceTableHeader[i]);
		
	}
	
	private void createDeleteInsertButtons() {
		
		createDeleteButtonsPanel = new JPanel();
		createDeleteButtonsPanel.setLayout(new BoxLayout(createDeleteButtonsPanel,0));
		
		deleteInvoiceButton = new JButton("Delete Invoice");
		insertInvoiceButton = new JButton("Create New Invoice");
		
		deleteInvoiceButton.setEnabled(false);
		insertInvoiceButton.setEnabled(false);
		MainFrame.createInvoiceMenuItem.setEnabled(false);

		
		createDeleteButtonsPanel.add(insertInvoiceButton);
		createDeleteButtonsPanel.add(deleteInvoiceButton);
		
	}

		
	private static void fillData(List<InvoiceHeader> invoiceHeaderList) {
		
		int len = invoiceHeaderList.size();
		InvoiceHeader currentInvoice ;
		
		TableModel invoiceTableModel = invoiceTable.getModel();
		((DefaultTableModel) invoiceTableModel).setRowCount(0);
		
		for (int i = 0 ; i < len; i++) {
			
			currentInvoice = invoiceHeaderList.get(i);
			((DefaultTableModel) invoiceTableModel).addRow(currentInvoice.getData());
			
		}
		
	}
	
	private void checkDate (String inputDate) throws DateTimeParseException{
	
		DateTimeFormatter f = DateTimeFormatter.ofPattern( "dd-MM-yyyy" );
		LocalDate.parse ( inputDate , f );
	
	}

	private void saveFileClick() {
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");   
		
		int userSelection = fileChooser.showSaveDialog(this);
		
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			invoiceTable.getSelectionModel().clearSelection();
		    String fileToSave = fileChooser.getSelectedFile().getAbsolutePath();
		    
		    try {
		    	
				fileOp.writeData(fileToSave);
				deleteInvoiceButton.setEnabled(true);
				
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		
		}
		
	}
	
	private void loadFileClick() {
		
		// Get header csv file
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to open");   
		
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.addChoosableFileFilter(new FileFilter() {
		    public String getDescription() {
		        return "CSV Files (*.csv)";
		    }
		 
		    public boolean accept(File f) {
		        if (f.isDirectory()) {
		            return true;
		        } else {
		            return f.getName().toLowerCase().endsWith("header.csv");
		        }
		    }
		});
		
		int userSelection = fileChooser.showOpenDialog(this);
		
		// process data
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			
			invoiceTable.getSelectionModel().clearSelection();
		    String fileToOpen = fileChooser.getSelectedFile().getAbsolutePath();
		    deleteInvoiceButton.setEnabled(true);
		    fileOp = new FileOperations(fileToOpen);
		    fileOp.readData();
		    List<InvoiceHeader> tableDataList = fileOp.getInvoiceData();
		    InvoiceItemsView.initiatFileOp(fileOp);
		    
		    // Reflect data to table 
		    fillData(tableDataList);
		    InvoiceItemsView.clear();
		    
			insertInvoiceButton.setEnabled(true);
			MainFrame.createInvoiceMenuItem.setEnabled(true);

			saveFileButton.setEnabled(true);

		    
			}
		
	}
	
	private void deleteInvoiceClick() {
		
		DefaultTableModel tableModel = (DefaultTableModel) invoiceTable.getModel();
		int rows = invoiceTable.getSelectedRow();
		if (rows >= 0) {
			
			tableModel.removeRow(rows);

			deleteInvoiceButton.setEnabled(false);
			saveFileButton.setEnabled(true);
			
			List<InvoiceHeader> invoiceHeaderData = fileOp.getInvoiceData();
			List<InvoiceItems> InvoiceItems = fileOp.getInvoiceItemData();
			
			
			int InvoiceItemsLen = InvoiceItems.size();
			
			String invoiceNToDelete = invoiceHeaderData.get(rows).getInvoiceNo();
			invoiceHeaderData.remove(rows);
			
			for (int i = 0 ; i < InvoiceItemsLen; i++)
				if (InvoiceItems.get(i).getItemNumber().equals(invoiceNToDelete)) {
					InvoiceItems.remove(i);
					InvoiceItemsLen -= 1;
					i-=1;
				}

			InvoiceItemsView.clear();
							
		}
		
	}
		
		

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if (arg0.getSource().equals(saveFileButton)) { saveFileClick();}
		else if (arg0.getSource().equals(loadFileButton)) {loadFileClick();}
		else if (arg0.getSource().equals(deleteInvoiceButton)) {deleteInvoiceClick();}
		else if (arg0.getSource().equals(insertInvoiceButton)) {insertInvoiceClick();}
		
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		
		 int row = invoiceTable.getSelectedRow();
		 // If there is a selected row
		 if (row >= 0) {
			 DefaultTableModel tableModel = (DefaultTableModel) invoiceTable.getModel(); 
			 String invoceNumber = (String) tableModel.getValueAt(row, 0);
			 // File details to right view labels 
			 InvoiceItemsView.updateItemTable(invoceNumber, (String) tableModel.getValueAt(row, 1),
			                                 (String) tableModel.getValueAt(row, 2), 
			                                 (String) tableModel.getValueAt(row, 3),
			                                 fileOp.getInvoiceItemData());
			 

			deleteInvoiceButton.setEnabled(true);
			InvoiceItemsView.saveButton.setEnabled(true);
	
		 }
		
	}

}
