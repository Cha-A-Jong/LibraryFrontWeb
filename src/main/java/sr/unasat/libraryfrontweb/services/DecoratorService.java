package sr.unasat.libraryfrontweb.services;

import sr.unasat.libraryfrontweb.dao.BookDAO;
import sr.unasat.libraryfrontweb.decorator.AudioBook;
import sr.unasat.libraryfrontweb.decorator.EBook;
import sr.unasat.libraryfrontweb.decorator.OnlineBook;

public class DecoratorService {
    BookDAO bookDAO = new BookDAO();

    public void decoratorApp(){
        bookDAO.findByTitle("The lord of the rings");

        OnlineBook audioBook = new AudioBook(); //casting ->creating an onlinebook object but referencing it to the audiobook
        OnlineBook eBook = new EBook(new AudioBook());

        System.out.println("Customer request to purchase an Audio Book");
        audioBook.getBook(bookDAO.findByTitle("The lord of the rings"));

        System.out.println("\n ");
        eBook.getBook(bookDAO.findByTitle("The Hobbit"));

    }
}
