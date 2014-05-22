package user.zoo;

import net.bodz.bas.fmt.rst.RstObject;

public class Animal
        extends RstObject {

    public String name;
    public boolean male;
    public int color;
    public int age;

    public Animal() {
    }

    public Animal(String name, boolean male, int color, int age) {
        this.name = name;
        this.male = male;
        this.color = color;
        this.age = age;
    }

}
