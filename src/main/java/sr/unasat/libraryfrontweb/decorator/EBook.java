package sr.unasat.libraryfrontweb.decorator;

import sr.unasat.libraryfrontweb.dao.BookDAO;
import sr.unasat.libraryfrontweb.entities.Book;

public class EBook extends OnlineBookDecorator implements OnlineBook {

    BookDAO bookDAO = new BookDAO();

    public EBook(OnlineBook bookDecorator) {
        super(bookDecorator);
    }

    @Override
    public void getBook(Book insertBook) {
        requestBook(bookDecorator);
        Book eBookInsert = Book.builder().title("The Hobbit").build();
        bookDAO.insertBook(eBookInsert);
        bookDecorator.getBook(eBookInsert);
    }

    private void requestBook(OnlineBook bookDecorator) {
        System.out.println("Customer request to purchase a Ebook version of: ");
    }
}
