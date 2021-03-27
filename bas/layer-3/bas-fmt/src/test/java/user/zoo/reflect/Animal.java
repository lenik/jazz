package user.zoo.reflect;

import net.bodz.bas.fmt.rst.AbstractRstObject;
import net.bodz.bas.fmt.xml.IXmlSerializable;

public class Animal
        extends AbstractRstObject
        implements
            IXmlSerializable {

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
