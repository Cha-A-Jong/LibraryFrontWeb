package sr.unasat.libraryfrontweb.dao;

import sr.unasat.libraryfrontweb.configuration.JPAConfig;
import sr.unasat.libraryfrontweb.entities.Book;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class BookDAO {
    private EntityManager entityManager = JPAConfig.getEntityManager();

    public List<Book> retrieveBookList() {
        entityManager.getTransaction().begin();
        String jpql = "select b from Book b";
        TypedQuery<Book> query = entityManager.createQuery(jpql, Book.class);
        List<Book> bookList = query.getResultList();
        entityManager.getTransaction().commit();
        return bookList;
    }

    public Book insertBook(Book book) {
        entityManager.getTransaction().begin();
        entityManager.persist(book);
        entityManager.getTransaction().commit();
        return book;
    }

    public int updateBook(Book book) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("UPDATE Book b SET b.title = :title where b.subtitle = :subtitle");
        query.setParameter("title", book.getTitle());
        query.setParameter("subtitle", book.getSubtitle());
        int rowsUpdated = query.executeUpdate();
        System.out.println("entities Updated: " + rowsUpdated);
        entityManager.getTransaction().commit();
        return rowsUpdated;
    }

    public int deleteBook(Long id) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("delete from Book b where b.id = :id");
        query.setParameter("id", id);
        int rowsDeleted = query.executeUpdate();
        System.out.println("entities deleted: " + rowsDeleted);
        entityManager.getTransaction().commit();
        return rowsDeleted;
    }

    public Book findByTitle(String title) {
        entityManager.getTransaction().begin();
        String jpql = "select b from Book b  where b.title = :title";
        TypedQuery<Book> query = entityManager.createQuery(jpql, Book.class);
        Book book = query.setParameter("title", title).getSingleResult();
        entityManager.getTransaction().commit();
        return book;
    }
    public Book findBookById(Long bookId) {
        entityManager.getTransaction().begin();
        String jpql = "select b from Book b  where b.id = :id";
        TypedQuery<Book> query = entityManager.createQuery(jpql, Book.class);
        Book book = query.setParameter("id", bookId).getSingleResult();
        entityManager.getTransaction().commit();
        return book;
    }
}
