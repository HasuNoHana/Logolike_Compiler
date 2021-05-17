package Parser;

import java.util.ArrayList;

public class Instruction extends ProgramFragments {

    private MemberAcess memberAcess;
    private Expresion assignment;
    public Instruction(MemberAcess memberAcess) {
        this.type = ProgramFragmentsType.MEMBERACESS;
        this.memberAcess = memberAcess;
    }

    public Instruction(MemberAcess memberAcess, Expresion assignment) {
        this.type = ProgramFragmentsType.ASSIGNMENT;
        this.memberAcess = memberAcess;
        this.assignment = assignment;
    }

    public MemberAcess getMemberAcess() {
        return memberAcess;
    }

    public Expresion getAssignment() {
        return assignment;
    }
}
