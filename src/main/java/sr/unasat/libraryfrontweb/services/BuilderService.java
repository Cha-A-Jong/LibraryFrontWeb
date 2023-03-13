package sr.unasat.libraryfrontweb.services;

import sr.unasat.libraryfrontweb.builder.Director;
import sr.unasat.libraryfrontweb.builder.Membership;
import sr.unasat.libraryfrontweb.builder.MembershipBuilder;

public class BuilderService {

    public void builderApp(){
        Director director = new Director();
        MembershipBuilder membershipBuilder = new MembershipBuilder();
        director.constructIndividualMembership(membershipBuilder);

        Membership membership = membershipBuilder.getResult();
        System.out.println("Membership built: " + membership.getMembershipType() + "\n" +
                "Membership fee:  "+ membership.getFee() + "\n" +
                "Privilege membership: " + membership.getPrivilege());
    }
}
