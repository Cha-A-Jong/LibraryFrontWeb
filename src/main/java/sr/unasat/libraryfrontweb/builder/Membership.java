package sr.unasat.libraryfrontweb.builder;

public class Membership {
    private final MembershipType membershipType;
    private final int fee;
    private final String privilege;

    public MembershipType getMembershipType() {
        return membershipType;
    }

    public int getFee() {
        return fee;
    }

    public String getPrivilege() {
        return privilege;
    }

    public Membership(MembershipType membershipType, int fee, String privilege){
        this.membershipType = membershipType;
        this.fee = fee;
        this.privilege = privilege;
    }
}
