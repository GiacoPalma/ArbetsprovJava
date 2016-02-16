import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;

public class AddBook extends JFrame {

	private JPanel contentPane;
	private JTextField authorField;
	private JTextField titelField;
	private JTextField priceField;
	private BookStock stock;
	private JTextField stockField;
	private JLabel lblAntalILager;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBook frame = new AddBook(null);
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
	public AddBook(BookStock stock) {
		this.stock = stock;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		authorField = new JTextField();
		authorField.setBounds(101, 13, 116, 22);
		contentPane.add(authorField);
		authorField.setColumns(10);
		
		titelField = new JTextField();
		titelField.setBounds(101, 48, 116, 22);
		contentPane.add(titelField);
		titelField.setColumns(10);
		
		priceField = new JTextField();
		priceField.setBounds(101, 83, 116, 22);
		contentPane.add(priceField);
		priceField.setColumns(10);
		
		stockField = new JTextField();
		stockField.setBounds(101, 120, 116, 22);
		contentPane.add(stockField);
		stockField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("F\u00F6rfattare");
		lblNewLabel.setBounds(12, 16, 77, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Titel");
		lblNewLabel_1.setBounds(12, 51, 56, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblPris = new JLabel("Pris");
		lblPris.setBounds(12, 86, 56, 16);
		contentPane.add(lblPris);
		
		JButton btnLggTill = new JButton("L\u00E4gg till");
		btnLggTill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BigDecimal price = new BigDecimal(priceField.getText());
				int stockAmount = Integer.parseInt(stockField.getText());
				Book book = new Book(titelField.getText(), authorField.getText(), price, stockAmount);
				boolean result = AddBook.this.stock.add(book, stockAmount);
				if(result){
					JOptionPane.showMessageDialog(contentPane, "Boken har lagts till i lagret!");
				}else{
					JOptionPane.showMessageDialog(contentPane, "Något fel uppstod! Försök igen");
				}
				AddBook.this.dispose();
			}
		});
		
		lblAntalILager = new JLabel("Antal i lager");
		lblAntalILager.setBounds(12, 123, 77, 16);
		contentPane.add(lblAntalILager);
		btnLggTill.setBounds(12, 217, 97, 25);
		contentPane.add(btnLggTill);
	}

}
