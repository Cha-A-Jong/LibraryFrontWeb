package sr.unasat.libraryfrontweb.builder;

public interface Builder {
    void setMembershipType(MembershipType type);
    void setFee(int fee);
    void setPrivilege(String privilege);
}
