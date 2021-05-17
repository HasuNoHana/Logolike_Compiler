package Parser;

import Parser.Expresion.Expresion;

import java.util.ArrayList;

public class Member {
    String name;
    ArrayList<Expresion> expresions;

    public Member(String name, ArrayList<Expresion> expresions) {
        this.name = name;
        this.expresions = expresions;
    }

    public Member(String name) {
        this.name = name;
        this.expresions = new ArrayList<Expresion>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Expresion> getExpresions() {
        return expresions;
    }
}
