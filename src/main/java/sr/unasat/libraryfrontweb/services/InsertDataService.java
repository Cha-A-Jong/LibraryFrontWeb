package sr.unasat.libraryfrontweb.services;


import sr.unasat.libraryfrontweb.dao.*;
import sr.unasat.libraryfrontweb.entities.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class InsertDataService {

    AuthorDAO authorDAO = new AuthorDAO();
    BookDAO bookDAO = new BookDAO();
    BorrowReceiptDAO borrowReceiptDAO = new BorrowReceiptDAO();
    GenreDAO genreDAO = new GenreDAO();
    MemberDAO memberDAO = new MemberDAO();

    public void insertDataAllTables(){

        Author authorInsert = Author.builder().firstName("Joanna").lastName("Rowling").build();
        authorDAO.insertAuthor(authorInsert);

        BorrowReceipt receiptInsert = BorrowReceipt.builder().borrow_date(LocalDate.of(2022,1,8)).due_date(LocalDate.of(2022,1,15)).receipt_number("2022-001").remark("laat ingeleverd").build();
        borrowReceiptDAO.insertReceipt(receiptInsert);

        Set<Member> memberSet = new HashSet<>();
        Member member = Member.builder().cbb_id_number("FI000794").date_of_birth("14-02-1990").firstName("Chanelle").lastName("Cha-A-Jong").library_number("202212001").borrowReceipt(receiptInsert).build();
        memberSet.add(member);
        memberDAO.insertMember(member);

        Book book = Book.builder().isbn("152663788X").subtitle(" ").title("Harry Potter and the Chamber of Secrets").author(authorInsert).borrowReceipt(receiptInsert)
                .members(memberSet).build();
        Book saveBookData = bookDAO.insertBook(book);

        Genre genreInsert = Genre.builder().name("Fantasy").book(book).build();
        genreDAO.insertGenre(genreInsert);

        System.out.println("The following records are inserted in the Book, Genre, Author, BorrowReceipt and Member tables: " +" \n "
                + saveBookData + member+genreInsert+receiptInsert+authorInsert);


    }
}
