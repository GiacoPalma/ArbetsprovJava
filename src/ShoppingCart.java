import java.math.BigDecimal;
import java.util.ArrayList;

public class ShoppingCart {
	private BigDecimal totalSum;
	private ArrayList<Book> items = new ArrayList<Book>();
	
	public boolean addToCart(Book book){
		if(book != null){
			items.add(book);
			
			return true;
		}else{
			return false;
		}
	}
	
	public boolean deleteFromCart(Book book){
		if(book !=null){
			items.remove(book);
		
			return true;
		}else{
			return false;
		}
	}
	
	public boolean emptyCart(){
		
		items.clear();
		
		return true;
	}
	
	public BigDecimal getSum(){
		BigDecimal sum = new BigDecimal(0);
		for(int i = 0; i < items.size(); i++){
			BigDecimal price = items.get(i).getPrice();
			sum = sum.add(price);
		}
				
		return sum;
	}
	public Book[] listItems(){
		Book[] books = new Book[items.size()];
		for(int i = 0; i < items.size(); i++){
			books[i] = items.get(i);
		}
		return books;
	}
}
