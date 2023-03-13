package sr.unasat.libraryfrontweb.controller;

import sr.unasat.libraryfrontweb.dto.BookDto;
import sr.unasat.libraryfrontweb.entities.Book;
import sr.unasat.libraryfrontweb.services.BookService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/book")
public class BookController {

    private BookService bookService = new BookService();
    @Path("/bookList")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> findAllBook(){
        return bookService.findAllBook();
    }

    @Path("/addBook")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Book createBook(BookDto bookDto){
        return bookService.createBookData(bookDto);
    }

    @Path("/deleteBook")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteRecordBook(Long deleteBookRecordId){
        bookService.deleteBookRecord(deleteBookRecordId);
    }

    @Path("/update")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBook(Book updateBook) {
        Book book = bookService.updateBookService(updateBook);
        if (book == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(book).build();
        }
    }
}
