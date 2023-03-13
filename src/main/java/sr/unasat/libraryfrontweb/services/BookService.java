package sr.unasat.libraryfrontweb.services;

import sr.unasat.libraryfrontweb.configuration.JPAConfig;
import sr.unasat.libraryfrontweb.dao.BookDAO;
import sr.unasat.libraryfrontweb.dto.BookDto;
import sr.unasat.libraryfrontweb.entities.*;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookService {

    BookDAO bookDAO = new BookDAO();
    private EntityManager entityManager = JPAConfig.getEntityManager();

    public List<Book> findAllBook() {return bookDAO.retrieveBookList();}

    public Book createBookData(BookDto bookDto) {

        Book book = new Book();
        try {
            Author author = entityManager.find(Author.class, bookDto.getAuthor().getId());
            BorrowReceipt borrowReceipt = entityManager.find(BorrowReceipt.class, bookDto.getBorrowReceipt().getId());
            Genre genre = entityManager.find(Genre.class, bookDto.getGenre().getId());

            Set<Member> members = new HashSet<>();
            for (Member member : bookDto.getMembers()) {
                Member dbMember = entityManager.find(Member.class, member.getId());
                members.add(dbMember);
            }

            book.setAuthor(author);
            book.setMembers(members);
            book.setIsbn(bookDto.getIsbn());
            book.setTitle(bookDto.getTitle());
            book.setSubtitle(bookDto.getSubtitle());

            bookDAO.insertBook(book);

            return book;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return book;

    }

    public void deleteBookRecord(Long deleteBookId){
        bookDAO.deleteBook(deleteBookId);
    }

    public Book updateBookService(Book updateBookRecord){
        Book findBook = bookDAO.findBookById(updateBookRecord.getId());
        if (findBook == null) {
            return null;
        }
        findBook.setIsbn(updateBookRecord.getIsbn());
        findBook.setTitle(updateBookRecord.getTitle());
        findBook.setSubtitle(updateBookRecord.getSubtitle());
        return entityManager.merge(findBook);
    }
}

