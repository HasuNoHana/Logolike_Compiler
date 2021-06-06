package Parser;

import Parser.Expresion.PrimaryExpresion;
import Parser.Expresion.PrimaryExpresionEnum;

import java.util.ArrayList;

public class MemberAcess extends PrimaryExpresion {
    private ArrayList<Member> members;

    public MemberAcess(ArrayList<Member> members) {
        this.members = members;
    }

    @Override
    public PrimaryExpresionEnum getPrimaryExpresionEnum() {
        return PrimaryExpresionEnum.MEMBERACESS;
    }

    public ArrayList<Member> getMembers() {
        return members;
    }
}
