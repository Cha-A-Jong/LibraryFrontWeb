package sr.unasat.libraryfrontweb.dao;

import sr.unasat.libraryfrontweb.configuration.JPAConfig;
import sr.unasat.libraryfrontweb.entities.Genre;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class GenreDAO {
    private EntityManager entityManager = JPAConfig.getEntityManager();

    public List<Genre> retrieveGenreList() {
        entityManager.getTransaction().begin();
        String jpql = "select g from Genre g";
        TypedQuery<Genre> query = entityManager.createQuery(jpql, Genre.class);
        List<Genre> genreList = query.getResultList();
        entityManager.getTransaction().commit();
        return genreList;
    }

    public Genre insertGenre(Genre genre) {
        entityManager.getTransaction().begin();
        entityManager.persist(genre);
        entityManager.getTransaction().commit();
        return genre;
    }

    public int updateGenre(Genre genre) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("UPDATE Genre g SET g.id = :id where g.name = :name");
        query.setParameter("id", genre.getId());
        query.setParameter("name", genre.getName());
        int rowsUpdated = query.executeUpdate();
        System.out.println("entities Updated: " + rowsUpdated);
        entityManager.getTransaction().commit();
        return rowsUpdated;
    }

    public int deleteGenre(String name) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("delete from Genre g where g.name = :name");
        query.setParameter("name", name);
        int rowsDeleted = query.executeUpdate();
        System.out.println("entities deleted: " + rowsDeleted);
        entityManager.getTransaction().commit();
        return rowsDeleted;
    }

    public Genre findGenreByName(String name) {
        entityManager.getTransaction().begin();
        String query = "select g from Genre g where g.name = :name";
        TypedQuery<Genre> typedQuery = entityManager.createQuery(query, Genre.class);
        Genre genre = typedQuery.setParameter("name", name).getSingleResult();
        entityManager.getTransaction().commit();
        return genre;
    }
}
