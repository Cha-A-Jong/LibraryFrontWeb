package sr.unasat.libraryfrontweb.controller;

import sr.unasat.libraryfrontweb.entities.Book;
import sr.unasat.libraryfrontweb.services.ReportsService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/reports")
public class ReportsController {

    private ReportsService reportService = new ReportsService();

    @Path("/quarter")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReportByQuarter(){
        List<Book> BorrowDateReportByQuarter = reportService.BorrowDateReportByQuarter();
        return Response.ok(BorrowDateReportByQuarter).build();

    }

    @Path("/year")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReportByYear(){
        List<Book> BorrowDateReportByYear = reportService.BorrowDateReportByYear();
        return Response.ok(BorrowDateReportByYear).build();
    }
}
