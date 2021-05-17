package Parser;

import Parser.BooleanExpresion.BooleanExpresion;
import Parser.Expresion.Expresion;

import java.util.ArrayList;

public class ForStatment extends ProgramFragments{
    private String i;
    private Expresion iAssign;
    private BooleanExpresion condition;
    private Expresion iUpdating;
    private ArrayList<ProgramFragments> insides;

    public ForStatment(String i, Expresion iAssign, BooleanExpresion condition, Expresion iUpdating, ArrayList<ProgramFragments> insides) {
        this.type = ProgramFragmentsType.FOR;
        this.i = i;
        this.iAssign = iAssign;
        this.condition = condition;
        this.iUpdating = iUpdating;
        this.insides = insides;
    }

    public String getI() {
        return i;
    }

    public Expresion getiAssign() {
        return iAssign;
    }

    public BooleanExpresion getCondition() {
        return condition;
    }

    public Expresion getiUpdating() {
        return iUpdating;
    }

    public ArrayList<ProgramFragments> getInsides() {
        return insides;
    }
}
