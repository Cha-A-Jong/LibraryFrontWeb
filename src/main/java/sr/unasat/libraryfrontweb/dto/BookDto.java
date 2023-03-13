package sr.unasat.libraryfrontweb.dto;

import sr.unasat.libraryfrontweb.entities.Author;
import sr.unasat.libraryfrontweb.entities.BorrowReceipt;
import sr.unasat.libraryfrontweb.entities.Genre;
import sr.unasat.libraryfrontweb.entities.Member;

import java.util.Set;

public class BookDto {
    private Long id;
    private String isbn;
    private String title;
    private String subtitle;
    private Genre genre;
    public Author author;
    private BorrowReceipt borrowReceipt;
    private Set<Member> members;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public BorrowReceipt getBorrowReceipt() {
        return borrowReceipt;
    }

    public void setBorrowReceipt(BorrowReceipt borrowReceipt) {
        this.borrowReceipt = borrowReceipt;
    }

    public Set<Member> getMembers() {
        return members;
    }

    public void setMembers(Set<Member> members) {
        this.members = members;
    }
}
