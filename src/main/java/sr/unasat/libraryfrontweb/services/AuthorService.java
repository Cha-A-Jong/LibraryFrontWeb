package sr.unasat.libraryfrontweb.services;


import sr.unasat.libraryfrontweb.dao.AuthorDAO;
import sr.unasat.libraryfrontweb.entities.Author;

import java.util.List;

public class AuthorService {

    AuthorDAO authorDAO = new AuthorDAO();

    public List<Author> findAllAuthor(){
        return authorDAO.retrieveAuthorList();
    }

}
