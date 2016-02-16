import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.nio.charset.StandardCharsets;

public class BookStock implements BookList {
	private Book[] books;
	private ArrayList<Book> booksList = new ArrayList<Book>();
	private static final int OK = 0;
	private static final int NOT_IN_STOCK = 1;
	private static final int DOES_NOT_EXIST = 2;
	
	public BookStock(){
		 booksList = getBookList();
		 books = new Book[booksList.size()];
		 for(int i = 0; i < booksList.size();i++){
		    	books[i] = booksList.get(i);
		 }
	
	}
	
	public ArrayList<Book> getBookList(){
		try {
			   URL url = new URL("http://www.contribe.se/bookstoredata/bookstoredata.txt");
			   BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
			   String inputLine;
			   int arrayIndex = 0;
			   ArrayList<Book> bookslist = new ArrayList<Book>();
			    while ((inputLine = in.readLine()) != null){
			    	String[] bookData = inputLine.split(";");
			    	BigDecimal price = new BigDecimal(bookData[2].replaceAll(",", ""));
			    	int stockAmount = new Integer(bookData[3]);
			    	Book book = new Book(bookData[0], bookData[1], price, stockAmount);
			    	bookslist.add(book);
			    	
			    }
			    in.close();
			    return bookslist;
			    
			   
			}
			catch(IOException ex) {
			   
			   ex.printStackTrace(); 
			}
		return null;
	}
	
	
	public Book[] getAll(){
		return books;
	}
	
	@Override
	public Book[] list(String searchString) {
		
		Book[] result;
		ArrayList<Book> bookList = new ArrayList<Book>();
		
		for(Book book: books){
			searchString = searchString.toLowerCase();
			String author = book.getAuthor().toLowerCase();
			String title = book.getTitle().toLowerCase();
			
			if(author.contains(searchString) || title.contains(searchString)){
				bookList.add(book);
			}
		}
		result = new Book[bookList.size()];
		for(int i = 0; i < bookList.size(); i++){
			result[i] = bookList.get(i);
		}
		return result;
	}

	@Override
	public boolean add(Book book, int amount) {
		if(book != null){
			booksList.add(book);
			books = new Book[booksList.size()];
			for(int i = 0; i < booksList.size();i++){
			   	books[i] = booksList.get(i);
			}
			return true;
		}else{

			return false;	
		}
		
	}

	@Override
	public int[] buy(Book... books) {
		int[] status = new int[books.length];
		
		for(int i = 0; i < books.length; i++){
			if(books[i].getStockAmount() > 0){
				status[i] = OK;
			}else if(books[i].getStockAmount() <= 0){
				status[i] = NOT_IN_STOCK;
			}else{
				status[i] = DOES_NOT_EXIST;
			}
		}
		return status;
	}

}
