import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.ScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class MainMenu {

	private JFrame frame;
	private JTextField textField;
	private BookStock stock;
	private Book[] books;
	private DefaultListModel listModel = new DefaultListModel();
	private ShoppingCart cart = new ShoppingCart();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu window = new MainMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//Initialize a new bookstock
		stock = new BookStock();
		frame = new JFrame();
		frame.setBounds(100, 100, 493, 436);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(12, 13, 116, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnSkBcker = new JButton("S\u00F6k b\u00F6cker");
		btnSkBcker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().length() > 0){
					books = stock.list(textField.getText());
					listModel.clear();
					if(books.length > 0){
						for(Book book : books){
							listModel.addElement(book.getAuthor() + " : " + book.getTitle() + "    Pris: " +book.getPrice()+ "kr       Antal: "+book.getStockAmount());
						}
					}else{
						listModel.addElement("Inga böcker hittades med dina sökord. Testa gärna andra sökord.");
					}
				}else{
					JOptionPane.showMessageDialog(frame, "Du måste ange sökord för att kunna söka!");
				}
			}
		});
		btnSkBcker.setBounds(146, 12, 116, 25);
		frame.getContentPane().add(btnSkBcker);
		
		JButton btnVisaAllaBcker = new JButton("Visa alla b\u00F6cker");
		btnVisaAllaBcker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listModel.clear();
				books = stock.getAll();
				for(Book book : books){
					listModel.addElement(book.getAuthor() + " : " + book.getTitle() + "    Pris:" +book.getPrice()+ "kr       Antal: "+book.getStockAmount());
				}
			}
		});
		btnVisaAllaBcker.setBounds(146, 56, 142, 25);
		frame.getContentPane().add(btnVisaAllaBcker);
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setBounds(12, 164, 387, 160);
		frame.getContentPane().add(scrollPane);
		
		final JList list = new JList(listModel);
		scrollPane.add(list);
		list.setBounds(12, 168, 383, 156);
		
		JButton btnLggIKundvagn = new JButton("L\u00E4gg i kundvagn");
		btnLggIKundvagn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selection[] = list.getSelectedIndices();
				if(selection.length > 0){
					for (int i = 0; i < selection.length; i++) {
						Book book = books[selection[i]];
						cart.addToCart(book);
					}
					JOptionPane.showMessageDialog(frame, "Kundvagnen har uppdaterats!");
					list.clearSelection();
				}else{
					JOptionPane.showMessageDialog(frame, "Du måste välja minst en bok att lägga till!");
				}
			}
		});
		btnLggIKundvagn.setBounds(12, 337, 136, 25);
		frame.getContentPane().add(btnLggIKundvagn);
		
		final JButton btnGTillKundvagn = new JButton("G\u00E5 till kundvagn");
		btnGTillKundvagn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ShoppingCartView cartView = new ShoppingCartView(cart, stock);
				cartView.setVisible(true);
				list.clearSelection();
			}
		});
		btnGTillKundvagn.setBounds(161, 337, 123, 25);
		frame.getContentPane().add(btnGTillKundvagn);
		
		JButton btnLggTillBok = new JButton("L\u00E4gg till bok");
		btnLggTillBok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddBook addBook = new AddBook(stock);
				addBook.setVisible(true);
			}
		});
		btnLggTillBok.setBounds(146, 99, 116, 25);
		frame.getContentPane().add(btnLggTillBok);
		
		
	}
}
