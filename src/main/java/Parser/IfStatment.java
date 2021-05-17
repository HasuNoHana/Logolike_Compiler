package Parser;

import Parser.BooleanExpresion.BooleanExpresion;
import Parser.Expresion.Expresion;

import java.util.ArrayList;

public class IfStatment extends ProgramFragments{

    private BooleanExpresion condition;
    private ArrayList<ProgramFragments> insides;
    private ArrayList<ProgramFragments> elseInsides;

    public IfStatment(BooleanExpresion condition, ArrayList<ProgramFragments> insides, ArrayList<ProgramFragments> elseInsides) {
        this.type = ProgramFragmentsType.IF;
        this.condition = condition;
        this.insides = insides;
        this.elseInsides = elseInsides;
    }

    public IfStatment(BooleanExpresion condition, ArrayList<ProgramFragments> insides) {
        this.type = ProgramFragmentsType.IF;
        this.condition = condition;
        this.insides = insides;
    }

    public BooleanExpresion getCondition() {
        return condition;
    }

    public ArrayList<ProgramFragments> getInsides() {
        return insides;
    }

    public ArrayList<ProgramFragments> getElseInsides() {
        return elseInsides;
    }
}
