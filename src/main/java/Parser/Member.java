package Parser;

import java.util.ArrayList;

public class Member {
    String name;
    ArrayList<Parameter> parameters;

    public Member(String name, ArrayList<Parameter> parameters) {
        this.name = name;
        this.parameters = parameters;
    }

    public Member(String name) {
        this.name = name;
        this.parameters = new ArrayList<Parameter>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Parameter> getParameters() {
        return parameters;
    }
}
