package sr.unasat.libraryfrontweb.dao;

import sr.unasat.libraryfrontweb.configuration.JPAConfig;
import sr.unasat.libraryfrontweb.entities.Author;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class AuthorDAO {
    private EntityManager entityManager = JPAConfig.getEntityManager();

    public List<Author> retrieveAuthorList() {
        entityManager.getTransaction().begin();
        String jpql = "select a from Author a";
        TypedQuery<Author> query = entityManager.createQuery(jpql, Author.class);
        List<Author> authorList = query.getResultList();
        entityManager.getTransaction().commit();
        return authorList;
    }

    //finds author and book
    public Author findAuthorByLastName(String lastName) {
        entityManager.getTransaction().begin();
        String query = "select a from Author a where a.lastName = :lastName";
        TypedQuery<Author> typedQuery = entityManager.createQuery(query, Author.class);
        Author author = typedQuery.setParameter("lastName", lastName).getSingleResult();
        entityManager.getTransaction().commit();
        return author;
    }

    public Author insertAuthor(Author author) {
        entityManager.getTransaction().begin();
        entityManager.persist(author);
        entityManager.getTransaction().commit();
        return author;
    }

    public int updateAuthor(Author author) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("UPDATE Author a SET a.firstName = :firstName where a.lastName = :lastName");
        query.setParameter("firstName", author.getFirstName());
        query.setParameter("lastName", author.getLastName());
        int rowsUpdated = query.executeUpdate();
        System.out.println("entities Updated: " + rowsUpdated);
        entityManager.getTransaction().commit();
        return rowsUpdated;
    }

    public int deleteAuthor(String firstName) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("delete from Author a where a.firstName = :firstName");
        query.setParameter("firstName", firstName);
        int rowsDeleted = query.executeUpdate();
        System.out.println("entities deleted: " + rowsDeleted);
        entityManager.getTransaction().commit();
        return rowsDeleted;
    }



}
