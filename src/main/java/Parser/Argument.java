package Parser;

public class Argument {
    private String type;
    private String name;

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == Argument.class){
            Argument a = (Argument) obj;
            return type.equals(a.getType()) && name.equals(a.getName());
        }
        return false;
    }

    public Argument(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
