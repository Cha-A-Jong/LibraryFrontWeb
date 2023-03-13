package sr.unasat.libraryfrontweb.services;

import sr.unasat.libraryfrontweb.dao.GenreDAO;
import sr.unasat.libraryfrontweb.entities.Genre;

import java.util.List;

public class GenreService {

    GenreDAO genreDAO = new GenreDAO();

    public void insertGenre(){
        Genre genre = Genre.builder().name("Fantasy").build();
        Genre saveGenre= genreDAO.insertGenre(genre);
        System.out.println("The following record is inserted: " +" \n "
                + saveGenre);
    }
    public void genreRetrieve(){
        List<Genre> genreList = genreDAO.retrieveGenreList();
        System.out.println("Retrieved record Genre: " + "\n" + genreList);
    }

    public void genreDelete(){
        Genre deleteGenre = genreDAO.findGenreByName("Thriller");
        genreDAO.deleteGenre("Thriller");
        System.out.println("The following records are deleted: " + deleteGenre);
    }

    public void genreUpdate(){
        Genre findGenre = genreDAO.findGenreByName("Fantasy");
        findGenre.setName("Thriller");
        genreDAO.updateGenre(findGenre);
        System.out.println("The following records are updated: " + findGenre);
    }
}
