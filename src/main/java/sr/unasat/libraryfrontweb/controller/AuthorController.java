package sr.unasat.libraryfrontweb.controller;

import sr.unasat.libraryfrontweb.entities.Author;
import sr.unasat.libraryfrontweb.services.AuthorService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/author")
public class AuthorController {

    private AuthorService authorService = new AuthorService();

    @GET
    @Path("/authorList")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllAuthor(){
        List<Author> authors = authorService.findAllAuthor();
        return Response.ok(authors).build();
    }
}
