package sr.unasat.libraryfrontweb.services;

import sr.unasat.libraryfrontweb.configuration.JPAConfig;
import sr.unasat.libraryfrontweb.dao.MemberDAO;
import sr.unasat.libraryfrontweb.dto.MemberDto;
import sr.unasat.libraryfrontweb.entities.Book;
import sr.unasat.libraryfrontweb.entities.BorrowReceipt;
import sr.unasat.libraryfrontweb.entities.Member;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MemberService {

    MemberDAO memberDAO = new MemberDAO();

    private EntityManager entityManager = JPAConfig.getEntityManager();
    public List<Member> getAllMember() {
        return memberDAO.retrieveMemberList();
    }

    public Member createMemberData(MemberDto memberDto) {

        Member member = new Member();
        try {
            BorrowReceipt borrowReceipt = entityManager.find(BorrowReceipt.class, memberDto.getBorrowReceipt().getId());

            Set<Book> books = new HashSet<>();
            for (Book book : memberDto.getBooks()) {
                Book dbBook = entityManager.find(Book.class, book.getId());
                books.add(dbBook);
            }

            member.setBorrowReceipt(borrowReceipt);
            member.setBooks(books);
            member.setFirstName(memberDto.getFirstName());
            member.setLastName(memberDto.getLastName());
            member.setDate_of_birth(memberDto.getDate_of_birth());
            member.setLibrary_number(memberDto.getLibrary_number());
            member.setCbb_id_number(memberDto.getCbb_id_number());

            memberDAO.insertMember(member);

            return member;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return member;

    }

    public void deleteBookRecord(Long deleteMemberId){
        memberDAO.deleteMember(deleteMemberId);
    }

    public Member updateMemberService(Member updateMemberRecord){
        Member findMember = memberDAO.findMemberById(updateMemberRecord.getId());
        if (findMember == null) {
            return null;
        }
        findMember.setFirstName(updateMemberRecord.getFirstName());
        findMember.setLastName(updateMemberRecord.getLastName());
        findMember.setDate_of_birth(updateMemberRecord.getDate_of_birth());
        findMember.setLibrary_number(updateMemberRecord.getLibrary_number());
        findMember.setCbb_id_number(updateMemberRecord.getCbb_id_number());
        return entityManager.merge(findMember);
    }
}
