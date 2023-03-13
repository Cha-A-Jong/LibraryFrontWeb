package sr.unasat.libraryfrontweb.controller;

import sr.unasat.libraryfrontweb.builder.Membership;
import sr.unasat.libraryfrontweb.builder.MembershipBuilder;
import sr.unasat.libraryfrontweb.services.MembershipService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/memberships")
public class MembershipController {

    private MembershipService membershipService = new MembershipService();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createMembership(Membership membershipRequest) {
        MembershipBuilder builder = new MembershipBuilder();
        builder.setMembershipType(membershipRequest.getMembershipType());
        builder.setFee(membershipRequest.getFee());
        builder.setPrivilege(membershipRequest.getPrivilege());
        Membership membership = null;
        switch (membershipRequest.getMembershipType()) {
            case STUDENT_MEMBERSHIP:
                membership = membershipService.createStudentMembership(builder);
                break;
            case INDIVIDUAL_MEMBERSHIP:
                membership = membershipService.createIndividualMembership(builder);
                break;
            case CHILD_MEMBERSHIP:
                membership = membershipService.createChildMembership(builder);
                break;
        }
        return Response.ok().entity(membership).build();
    }
}
