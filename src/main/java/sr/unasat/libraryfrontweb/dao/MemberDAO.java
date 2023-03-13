package sr.unasat.libraryfrontweb.dao;

import sr.unasat.libraryfrontweb.configuration.JPAConfig;
import sr.unasat.libraryfrontweb.entities.Member;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class MemberDAO {
    private EntityManager entityManager = JPAConfig.getEntityManager();

    public List<Member> retrieveMemberList() {
        entityManager.getTransaction().begin();
        String jpql = "select m from Member m";
        TypedQuery<Member> query = entityManager.createQuery(jpql, Member.class);
        List<Member> memberList = query.getResultList();
        entityManager.getTransaction().commit();
        return memberList;
    }

    public Member insertMember(Member member) {
        entityManager.getTransaction().begin();
        entityManager.persist(member);
        entityManager.getTransaction().commit();
        return member;
    }

    public int updateMember(Member member) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("UPDATE Member m SET m.firstName = :firstName where m.lastName = :lastName");
        query.setParameter("firstName", member.getFirstName());
        query.setParameter("lastName", member.getLastName());
        int rowsUpdated = query.executeUpdate();
        System.out.println("entities Updated: " + rowsUpdated);
        entityManager.getTransaction().commit();
        return rowsUpdated;
    }

    public int deleteMember(Long id) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("delete from Member m where m.id = :id");
        query.setParameter("id", id);
        int rowsDeleted = query.executeUpdate();
        System.out.println("entities deleted: " + rowsDeleted);
        entityManager.getTransaction().commit();
        return rowsDeleted;
    }

    public Member findMemberByLastName(String lastName) {
        entityManager.getTransaction().begin();
        String query = "select m from Member m where m.lastName = :lastName";
        TypedQuery<Member> typedQuery = entityManager.createQuery(query, Member.class);
        Member member = typedQuery.setParameter("lastName", lastName).getSingleResult();
        entityManager.getTransaction().commit();
        return member;
    }

    public Member findMemberById(Long memberId) {
        entityManager.getTransaction().begin();
        String jpql = "select m from Member m  where m.id = :id";
        TypedQuery<Member> query = entityManager.createQuery(jpql, Member.class);
        Member member = query.setParameter("id", memberId).getSingleResult();
        entityManager.getTransaction().commit();
        return member;
    }
}
