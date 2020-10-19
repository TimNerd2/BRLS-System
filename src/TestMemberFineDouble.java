import java.util.Date;

import library.borrowbook.BorrowBookControl;
import library.borrowbook.BorrowBookUI;
import library.entities.*;
import library.entities.helpers.BookHelper;
import library.entities.helpers.CalendarFileHelper;
import library.entities.helpers.LoanHelper;
import library.entities.helpers.PatronHelper;
import library.returnbook.ReturnBookControl;
import library.returnbook.ReturnBookUI;

public class TestMemberFineDouble {
	
	public static void main(String[] args) {
		CalendarFileHelper calendarHelper = new CalendarFileHelper();
		ICalendar cal = calendarHelper.loadCalendar();
		Date currentDate = new Date();
		Library library = new Library(new BookHelper(), new PatronHelper(), new LoanHelper());
		library.addBook("a", "t", "c1");
		
		library.addPatron("j", "t", "test1", 042321);
		
		BorrowBookControl borrowBook = new BorrowBookControl(library);
		BorrowBookUI borrowBookUI = new BorrowBookUI(borrowBook);
		
		System.out.println(library.getPatronList());
		System.out.println("");
		System.out.println("");
		
		borrowBook.cardSwiped(1);
		borrowBook.bookScanned(1);
		borrowBook.borrowingCompleted();
		borrowBook.commitLoans();
		
		//Print out some blank lines to make it easier to read
		System.out.println("");
		System.out.println("");
		
		cal.incrementDate(4);
		
		library.checkCurrentLoansOverDue();
		
		System.out.println(cal.getDate());
		
		ReturnBookControl returnBook = new ReturnBookControl(library);
		ReturnBookUI returnBookUI = new ReturnBookUI(returnBook);
		
		returnBook.bookScanned(1);
		
		returnBook.dischargeLoan(false);
		
		returnBook.scanningComplete();
		
		System.out.println("");
		System.out.println("");
		
		System.out.println(library.getPatronList());
	}
}
