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

    public void constructChildMembership(Builder builder){
        builder.setMembershipType(MembershipType.FAMILY_MEMBERSHIP);
        builder.setFee(50);
        builder.setPrivilege("For applicants aged 8 to 16");
    }
}
