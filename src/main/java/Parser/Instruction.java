package Parser;

import java.util.ArrayList;

public class Instruction extends ProgramFragments {

    private ArrayList<Member> members;
    public Instruction(ArrayList<Member> members) {
        this.type = "Instruction";
        this.members = members;
    }

    public ArrayList<Member> getMembers() {
        return members;
    }
}
