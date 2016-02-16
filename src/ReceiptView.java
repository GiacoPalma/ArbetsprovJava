import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.ScrollPane;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;

public class ReceiptView extends JFrame {

	private JPanel contentPane;
	private Book[] cartItems;
	private int[] status;
	private DefaultListModel listModel = new DefaultListModel();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReceiptView frame = new ReceiptView(null, null, null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ReceiptView(int[] status, Book[] cartItems, BigDecimal totalSum) {
		this.status = status;
		this.cartItems = cartItems;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setBounds(10, 13, 410, 191);
		contentPane.add(scrollPane);
		
		JList list = new JList(listModel);
		list.setBounds(12, 13, 408, 191);
		scrollPane.add(list);
		
		for (int i = 0; i < cartItems.length; i++){
			Book book = cartItems[i];
			int bookStatus = status[i];
			String buyStatus = "";
			switch(bookStatus){
				case 0: buyStatus = "OK";
				break;
				
				case 1: buyStatus = "Inte i lager";
				break;
				
				case 2: buyStatus = "Boken existerar inte";
				break;
			}
			listModel.addElement(book.getTitle()+ " : " + book.getAuthor());
			listModel.addElement("Pris: " +book.getPrice());
			listModel.addElement("Köpstatus: " +buyStatus);
			listModel.addElement("-----------------------");
		}
		listModel.addElement("Totala Köpsumman: "+ totalSum + "kr");
		
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnOk.setBounds(171, 217, 97, 25);
		contentPane.add(btnOk);
	}

}
