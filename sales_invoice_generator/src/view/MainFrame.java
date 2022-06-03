

package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


public class MainFrame extends JFrame  implements  ActionListener{
	
	private static final long serialVersionUID = 1L;
	

	private JMenuBar menuBar;
	private JMenu fileMenu;
	static JMenuItem createInvoiceMenuItem;

	private InvoiceHeaderView invoiceHeader;
	private InvoiceItemsView invoiceItems;

	
	public MainFrame() {
		
		setDefaultParameters();
		createMenu();

		invoiceHeader = new InvoiceHeaderView();
		JPanel leftView = invoiceHeader.getView();
		
		invoiceItems = new InvoiceItemsView();
		JPanel rightView = invoiceItems.getView();
		
		setLayout(new BoxLayout(getContentPane(),0));
		add(leftView);		
		add(rightView);	
				
	}
	
	private void setDefaultParameters() {
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 500);
		
	}
	
	private void createMenu() {
		
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		createInvoiceMenuItem = new JMenuItem("Create Invoice");
		createInvoiceMenuItem.addActionListener(this);
		createInvoiceMenuItem.setEnabled(false);
		fileMenu.add(createInvoiceMenuItem);
		menuBar.add(fileMenu);
		setJMenuBar(menuBar);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(createInvoiceMenuItem))
			invoiceHeader.insertInvoiceClick();
		
	}

}
