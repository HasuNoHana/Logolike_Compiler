package Parser;

import java.util.ArrayList;

public class Instruction extends ProgramFragments {

    private MemberAcess memberAcess;
    public Instruction(MemberAcess memberAcess) {
        this.type = "Instruction";
        this.memberAcess = memberAcess;
    }

    public MemberAcess getMemberAcess() {
        return memberAcess;
    }
}
