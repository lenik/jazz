package user.zoo.reflect;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.api.ElementHandlerException;
import net.bodz.bas.fmt.rst.AbstractRstObject;
import net.bodz.bas.fmt.rst.IRstHandler;
import net.bodz.bas.fmt.xml.IXmlForm;

public class Zoo
        extends AbstractRstObject
        implements
            IXmlForm {

    public String owner;
    public int createdYear;

    public List<Cat> cats = new ArrayList<Cat>();
    public List<Dog> dogs = new ArrayList<Dog>();

    @Override
    public IRstHandler beginChild(String name, String[] args)
            throws ParseException, ElementHandlerException {
        if ("cat".equals(name)) {
            Cat cat = new Cat();
            cats.add(cat);
            return cat;
        }
        if ("dog".equals(name)) {
            Dog dog = new Dog();
            dogs.add(dog);
            return dog;
        }
        return null;
    }

}
