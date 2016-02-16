import java.math.BigDecimal;

public class Book {
	
	private String title;
	private String author;
	private BigDecimal price;
	private int stockAmount;
	
	public Book(String title, String author, BigDecimal price, int stockAmount) {
		this.title = title;
		this.author = author;
		this.price = price;
		this.stockAmount = stockAmount;
	}
	
	public String getTitle(){
		return this.title;
	}
	public String getAuthor(){
		return this.author;
	}
	public BigDecimal getPrice(){
		return this.price;
	}
	public int getStockAmount(){
		return stockAmount;
	}
}
