package sr.unasat.libraryfrontweb.dao;

import sr.unasat.libraryfrontweb.configuration.JPAConfig;
import sr.unasat.libraryfrontweb.entities.Book;
import sr.unasat.libraryfrontweb.entities.BorrowReceipt;
import sr.unasat.libraryfrontweb.entities.Member;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class BorrowReceiptDAO {
    private EntityManager entityManager = JPAConfig.getEntityManager();

    public List<BorrowReceipt> retrieveReceiptList() {
        entityManager.getTransaction().begin();
        String jpql = "select b from BorrowReceipt b";
        TypedQuery<BorrowReceipt> query = entityManager.createQuery(jpql, BorrowReceipt.class);
        List<BorrowReceipt> receiptList = query.getResultList();
        entityManager.getTransaction().commit();
        return receiptList;
    }

    public BorrowReceipt insertReceipt(BorrowReceipt borrowReceipt) {
        entityManager.getTransaction().begin();
        entityManager.persist(borrowReceipt);
        entityManager.getTransaction().commit();
        return borrowReceipt;
    }

    public int updateReceipt(BorrowReceipt borrowReceipt) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("UPDATE BorrowReceipt b SET b.receipt_number = :receipt_number where b.borrow_date = :borrow_date");
        query.setParameter("receipt_number", borrowReceipt.getReceipt_number());
        query.setParameter("borrow_date", borrowReceipt.getBorrow_date());
        int rowsUpdated = query.executeUpdate();
        System.out.println("entities Updated: " + rowsUpdated);
        entityManager.getTransaction().commit();
        return rowsUpdated;
    }

    public int deleteReceipt(Long id) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("delete from BorrowReceipt br where br.id = :id");
        query.setParameter("id", id);
        int rowsDeleted = query.executeUpdate();
        System.out.println("entities deleted: " + rowsDeleted);
        entityManager.getTransaction().commit();
        return rowsDeleted;
    }

    public BorrowReceipt findReceiptByNumber(String receipt_number) {
        entityManager.getTransaction().begin();
        String query = "select b from BorrowReceipt b where b.receipt_number = :receipt_number";
        TypedQuery<BorrowReceipt> typedQuery = entityManager.createQuery(query, BorrowReceipt.class);
        BorrowReceipt borrowReceipt = typedQuery.setParameter("receipt_number", receipt_number).getSingleResult();
        entityManager.getTransaction().commit();
        return borrowReceipt;
    }

    public List<Book> findBorrowDateByQuarter(LocalDate borrow_date){
        entityManager.getTransaction().begin();
        String jpql = "select b from Book b join BorrowReceipt br on b.borrowReceipt.id = br.id where QUARTER(br.borrow_date) in (1) and br.remark = :remark";
        TypedQuery<Book> query = entityManager.createQuery(jpql, Book.class);
        List<Book> bookList = query.setParameter("remark", "laat ingeleverd").getResultList();
        entityManager.getTransaction().commit();
        return bookList;
    }

    public List<Book> findBorrowDateByYear(LocalDate borrow_date){
        entityManager.getTransaction().begin();
        String jpql = "select b from Book b join BorrowReceipt br on b.borrowReceipt.id = br.id where br.borrow_date = :borrow_date and YEAR(br.borrow_date) =:year";
        TypedQuery<Book> query = entityManager.createQuery(jpql, Book.class);
        List<Book> bookList = query.setParameter("borrow_date", borrow_date).setParameter("year", borrow_date.getYear()).getResultList();
        entityManager.getTransaction().commit();
        return bookList;
    }

    public int deleteOneRecord(Member member){
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("delete from BorrowReceipt br" +
                " where br.member = :member");
        query.setParameter("member", member);
        int rowsDeleted = query.executeUpdate();
        entityManager.getTransaction().commit();
        return rowsDeleted;
    }

    public BorrowReceipt findBorrowReceiptById(Long borrowreceiptId){
        entityManager.getTransaction().begin();
        String jpql = "select br from BorrowReceipt br where br.id = :id";
        TypedQuery<BorrowReceipt> query = entityManager.createQuery(jpql, BorrowReceipt.class);
        BorrowReceipt borrowReceipt = query.setParameter("id", borrowreceiptId).getSingleResult();
        entityManager.getTransaction().commit();
        return borrowReceipt;
    }
}
