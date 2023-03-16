package sr.unasat.libraryfrontweb.controller;

import sr.unasat.libraryfrontweb.builder.Membership;
import sr.unasat.libraryfrontweb.builder.MembershipBuilder;
import sr.unasat.libraryfrontweb.builder.MembershipType;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/memberships")
public class MembershipController {
    @POST
    @Path("/getMembership")
    @Produces(MediaType.APPLICATION_JSON)
    public Membership getMembership(@FormParam("membershipType") String membershipType) {
        MembershipBuilder builder = new MembershipBuilder();

        if (membershipType.equals("INDIVIDUAL_MEMBERSHIP")) {
            builder.setMembershipType(MembershipType.INDIVIDUAL_MEMBERSHIP);
            builder.setFee(150);
            builder.setPrivilege("Full access to all books, Ebooks and Audiobooks");
        } else if (membershipType.equals("FAMILY_MEMBERSHIP")) {
            builder.setMembershipType(MembershipType.FAMILY_MEMBERSHIP);
            builder.setFee(250);
            builder.setPrivilege("Full access to all books, Ebooks and Audiobooks for family members");
        } else if (membershipType.equals("STUDENT_MEMBERSHIP")) {
            builder.setMembershipType(MembershipType.STUDENT_MEMBERSHIP);
            builder.setFee(75);
            builder.setPrivilege("Access to textbooks and course materials");
        }

        return builder.getResult();
    }
}

