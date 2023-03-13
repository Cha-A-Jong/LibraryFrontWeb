package sr.unasat.libraryfrontweb.decorator;


import sr.unasat.libraryfrontweb.entities.Book;

public class AudioBook implements OnlineBook{

    @Override
    public void getBook(Book insertBook) {
        System.out.println(insertBook.getTitle());
    }
}
