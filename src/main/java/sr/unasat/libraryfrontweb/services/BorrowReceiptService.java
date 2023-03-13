package sr.unasat.libraryfrontweb.services;

import sr.unasat.libraryfrontweb.configuration.JPAConfig;
import sr.unasat.libraryfrontweb.dao.BookDAO;
import sr.unasat.libraryfrontweb.dao.BorrowReceiptDAO;
import sr.unasat.libraryfrontweb.dto.BorrowReceiptDto;
import sr.unasat.libraryfrontweb.entities.Book;
import sr.unasat.libraryfrontweb.entities.BorrowReceipt;
import sr.unasat.libraryfrontweb.entities.Member;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BorrowReceiptService {

    BorrowReceiptDAO borrowReceiptDAO = new BorrowReceiptDAO();
    private EntityManager entityManager = JPAConfig.getEntityManager();

    public List<BorrowReceipt> findAllReceipt() {return borrowReceiptDAO.retrieveReceiptList();}

    public BorrowReceipt createBorrowReceiptData(BorrowReceiptDto borrowReceiptDto){

        BorrowReceipt borrowReceipt = new BorrowReceipt();
        try {
            Member member = entityManager.find(Member.class, borrowReceiptDto.getMember().getId());

            Set<Book> books = new HashSet<>();
            for (Book book : borrowReceiptDto.getBooks()){
                Book dbBook = entityManager.find(Book.class, book.getId());
                books.add(dbBook);
            }

            borrowReceipt.setMember(member);
            borrowReceipt.setBooks(books);
            borrowReceipt.setReceipt_number(borrowReceiptDto.getReceipt_number());
            borrowReceipt.setBorrow_date(borrowReceiptDto.getBorrow_date());
            borrowReceipt.setDue_date(borrowReceiptDto.getDue_date());
            borrowReceipt.setRemark(borrowReceiptDto.getRemark());

            borrowReceiptDAO.insertReceipt(borrowReceipt);
            return borrowReceipt;
         } catch (Exception e) {
            e.printStackTrace();
        }

        return borrowReceipt;

    }

    public void deleteBorrowReceiptRecord(Long deleteReceiptId) {borrowReceiptDAO.deleteReceipt(deleteReceiptId);}

    public BorrowReceipt updateBorrowReceiptService(BorrowReceipt updateReceiptRecord){
        BorrowReceipt findBorrowReceipt = borrowReceiptDAO.findBorrowReceiptById(updateReceiptRecord.getId());
        if (findBorrowReceipt == null) {
            return null;
        }
        findBorrowReceipt.setReceipt_number(updateReceiptRecord.getReceipt_number());
        findBorrowReceipt.setBorrow_date(updateReceiptRecord.getBorrow_date());
        findBorrowReceipt.setDue_date(updateReceiptRecord.getDue_date());
        findBorrowReceipt.setRemark(updateReceiptRecord.getRemark());
        return entityManager.merge(findBorrowReceipt);
    }


}
