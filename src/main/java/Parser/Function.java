package Parser;

import java.util.ArrayList;
import java.util.List;

public class Function {
    private String name;
    private String type;
    private ArrayList<ProgramFragments> programFragments;
    private ArrayList<Argument> arguments;

    public Function(String name, String type, ArrayList<Argument> arguments, ArrayList<ProgramFragments> insides) {
        this.name = name;
        this.type = type;
        this.programFragments = insides;
        this.arguments = arguments;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Argument> getArguments() {
        return this.arguments;
    }

    public List<ProgramFragments> getInsides() {
        return this.programFragments;
    }
}
