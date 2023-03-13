package sr.unasat.libraryfrontweb.services;

import sr.unasat.libraryfrontweb.dao.BorrowReceiptDAO;
import sr.unasat.libraryfrontweb.entities.Book;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReportsService {

    BorrowReceiptDAO borrowReceiptDAO = new BorrowReceiptDAO();

    public List<Book> BorrowDateReportByQuarter(){
        List<Book> borrowDateQuarterReport = borrowReceiptDAO.findBorrowDateByQuarter(LocalDate.parse("2022-01-08", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        System.out.println(borrowDateQuarterReport);
        return borrowDateQuarterReport;
    }

    public List<Book> BorrowDateReportByYear(){
        List<Book> borrowDateYearReport = borrowReceiptDAO.findBorrowDateByYear(LocalDate.parse("2022-01-08", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        System.out.println(borrowDateYearReport);
        return borrowDateYearReport;
    }
}
