import library.*;
import library.entities.*;
import library.entities.helpers.BookHelper;
import library.entities.helpers.LoanHelper;
import library.entities.helpers.PatronHelper;

public class TestReturnBookBug {
	public static void main(String[] args) {
		Library library = new Library(new BookHelper(), new PatronHelper(), new LoanHelper());
		
		// Create our book and patron
		Book book = new Book("a","t","c1",1);
		
		Patron patron = new Patron("j","t","test1",042042,1);
	}
}
