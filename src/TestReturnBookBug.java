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

public class TestReturnBookBug {
	public static void main(String[] args) {
		
		CalendarFileHelper calendarHelper = new CalendarFileHelper();
		ICalendar cal = calendarHelper.loadCalendar();
		Date currentDate = new Date();
		
		cal.setDate(currentDate);
		Library library = new Library(new BookHelper(), new PatronHelper(), new LoanHelper());
		
		// Create our book and patron
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
		

		
		//Increment by one day. (Makes it go to the day after the due date for some reason)
		cal.incrementDate(3);
		
		library.checkCurrentLoansOverDue();
		
		System.out.println(cal.getDate());
		// Execute the return book use case.
		ReturnBookControl returnBook = new ReturnBookControl(library);
		ReturnBookUI returnBookUI = new ReturnBookUI(returnBook);
		
		returnBook.bookScanned(1);
		
		returnBook.dischargeLoan(false);
		
		returnBook.scanningComplete();
		
		System.out.println("");
		System.out.println("");
		
		// After all of this has completed, we should test to see if the fine gets added to the patron, if it is still 0, this means the bug is present still.
		System.out.println(library.getPatronList());
		
		
	}
}
