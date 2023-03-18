package sr.unasat.libraryfrontweb.builder;

public class Director {

    public void constructStudentMembership(Builder builder){
        builder.setMembershipType(MembershipType.STUDENT_MEMBERSHIP);
        builder.setFee(75);
        builder.setPrivilege("Full access to all books, limited access to Ebooks and Audiobooks");
    }

    public void constructIndividualMembership(Builder builder){
        builder.setMembershipType(MembershipType.INDIVIDUAL_MEMBERSHIP);
        builder.setFee(150);
        builder.setPrivilege("Full access to all books, Ebooks and Audiobooks");
    }

    public void constructFamilyMembership(Builder builder){
        builder.setMembershipType(MembershipType.FAMILY_MEMBERSHIP);
        builder.setFee(300);
        builder.setPrivilege("Full access to all books, Ebooks and Audiobooks for family members up to 6 people");
    }
}
