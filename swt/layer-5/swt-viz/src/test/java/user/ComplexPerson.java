package user;

public class ComplexPerson {

    public String name;

    /**
     * @style background-color: #ffffcc
     */
    public ASL asl;

    public void setName(String name) {
        this.name = name;
    }

    public void setASL(ASL asl) {
        this.asl = asl;
    }

    @Override
    public String toString() {
        return "<" + name + ": " + asl + ">";
    }

}
