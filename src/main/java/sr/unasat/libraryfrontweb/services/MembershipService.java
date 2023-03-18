package sr.unasat.libraryfrontweb.services;

import sr.unasat.libraryfrontweb.builder.Director;
import sr.unasat.libraryfrontweb.builder.Membership;
import sr.unasat.libraryfrontweb.builder.MembershipBuilder;

public class MembershipService {

    public Membership createStudentMembership(MembershipBuilder builder) {
        Director director = new Director();
        director.constructStudentMembership(builder);
        return builder.getResult();
    }

    public Membership createIndividualMembership(MembershipBuilder builder) {
        Director director = new Director();
        director.constructIndividualMembership(builder);
        return builder.getResult();
    }

    public Membership createFamilyMembership(MembershipBuilder builder) {
        Director director = new Director();
        director.constructFamilyMembership(builder);
        return builder.getResult();
    }
}


