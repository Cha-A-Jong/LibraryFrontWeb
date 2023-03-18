package sr.unasat.libraryfrontweb.controller;

import sr.unasat.libraryfrontweb.dto.MemberDto;
import sr.unasat.libraryfrontweb.entities.Member;
import sr.unasat.libraryfrontweb.services.MemberService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/member")
public class MemberController {

    private MemberService memberService = new MemberService();
    @Path("/memberList")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Member> findAllMembers(){
        return memberService.getAllMember();
    }

    @Path("/addMember")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Member createMember(MemberDto memberDto){
        return memberService.createMemberData(memberDto);
    }

    @Path("/deleteMember")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteRecordMember(Long deleteMemberRecordId){
        memberService.deleteBookRecord(deleteMemberRecordId);
    }

    @Path("/update")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMember(Member updateMember) {
        Member member = memberService.updateMemberService(updateMember);
        if (member == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(member).build();
        }
    }
}
