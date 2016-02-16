import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.ScrollPane;

public class ShoppingCartView extends JFrame {

	private JPanel contentPane;
	private ShoppingCart cart;
	private BookStock stock;
	private Book[] cartItems;
	private DefaultListModel listModel = new DefaultListModel();
	private JLabel lblKr;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShoppingCartView frame = new ShoppingCartView(null, null);
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
	public ShoppingCartView(ShoppingCart cart, BookStock stock) {
		this.cart = cart;
		this.stock = stock;
		cartItems = cart.listItems();
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 314);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setBounds(9, 13, 411, 170);
		contentPane.add(scrollPane);
		
		final JList list = new JList(listModel);
		list.setBounds(12, 13, 408, 170);
		scrollPane.add(list);
		
		for(Book item : cartItems){
			listModel.addElement(item.getAuthor() + " : " + item.getTitle() + " " +item.getPrice()+ "kr");
		}
		
		JButton btnTaBort = new JButton("Ta bort");
		btnTaBort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cartItems = ShoppingCartView.this.cart.listItems();
				int selection[] = list.getSelectedIndices();
				for (int i = selection.length-1; i >= 0; i--) {
					Book book = cartItems[selection[i]];
					System.out.println(cartItems[selection[i]].getTitle());
					System.out.println(selection[i]);
					System.out.println(listModel.size());
					listModel.remove(selection[i]);
					
					ShoppingCartView.this.cart.deleteFromCart(book);
					
					lblKr.setText(ShoppingCartView.this.cart.getSum()+ " kr");
				}
				
			}
		});
		btnTaBort.setBounds(12, 228, 97, 25);
		contentPane.add(btnTaBort);
		
		JButton btnKp = new JButton("K\u00F6p");
		btnKp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cartItems = ShoppingCartView.this.cart.listItems();
				if(cartItems.length > 0){
					int[] buyStatus = ShoppingCartView.this.stock.buy(cartItems);
					BigDecimal totalSum = ShoppingCartView.this.cart.getSum();
					ReceiptView receipt = new ReceiptView(buyStatus, cartItems, totalSum);
					receipt.setVisible(true);
				}else{
					JOptionPane.showMessageDialog(contentPane, "Kundvagnen är tom!");
				}
			}
		});
		btnKp.setBounds(323, 228, 97, 25);
		contentPane.add(btnKp);
		
		JButton btnTmKundvagn = new JButton("T\u00F6m kundvagn");
		btnTmKundvagn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean status = ShoppingCartView.this.cart.emptyCart();
				if(status){
					JOptionPane.showMessageDialog(contentPane, "Kundvagnen har tömts!");
					listModel.clear();
					lblKr.setText("0 kr");
				}
			}
		});
		btnTmKundvagn.setBounds(156, 228, 117, 25);
		contentPane.add(btnTmKundvagn);
		
		JLabel lblSumma = new JLabel("Summa: ");
		lblSumma.setBounds(12, 196, 65, 16);
		contentPane.add(lblSumma);
		
		lblKr = new JLabel(cart.getSum()+" kr");
		lblKr.setBounds(69, 196, 245, 16);
		contentPane.add(lblKr);
		
		
	}

}
