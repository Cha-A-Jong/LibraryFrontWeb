package sr.unasat.libraryfrontweb.controller;

import sr.unasat.libraryfrontweb.builder.Director;
import sr.unasat.libraryfrontweb.builder.Membership;
import sr.unasat.libraryfrontweb.builder.MembershipBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/memberships")
public class MembershipController {
    @GET
    @Path("/getMembership")
    @Produces(MediaType.APPLICATION_JSON)
    public Membership getMembership(@QueryParam("membershipType") String membershipType) {
        if (membershipType == null) {
            throw new IllegalArgumentException("Membership type is null");
        }

        MembershipBuilder builder = new MembershipBuilder();

        switch(membershipType) {
            case "INDIVIDUAL_MEMBERSHIP":
                Director director1 = new Director();
                director1.constructIndividualMembership(builder);
                break;
            case "FAMILY_MEMBERSHIP":
                Director director2 = new Director();
                director2.constructFamilyMembership(builder);
                break;
            case "STUDENT_MEMBERSHIP":
                Director director3 = new Director();
                director3.constructStudentMembership(builder);
                break;
            default:
                throw new IllegalArgumentException("Invalid membership type: " + membershipType);
        }

        return builder.getResult();
    }
}


