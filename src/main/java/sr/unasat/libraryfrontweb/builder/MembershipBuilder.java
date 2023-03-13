package sr.unasat.libraryfrontweb.builder;

public class MembershipBuilder implements Builder {

    private MembershipType type;
    private int fee;
    private String privilege;

    @Override
    public void setMembershipType(MembershipType type) {
        this.type = type;

    }

    @Override
    public void setFee(int fee) {
        this.fee = fee;
    }

    @Override
    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public Membership getResult(){
        return new Membership(type, fee, privilege);
    }

}


