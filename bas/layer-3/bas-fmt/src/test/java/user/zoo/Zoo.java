package user.zoo;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.rst.ElementHandlerException;
import net.bodz.bas.fmt.rst.IElementHandler;
import net.bodz.bas.fmt.rst.RstObject;

public class Zoo
        extends RstObject {

    public String owner;
    public int createdYear;

    public List<Cat> cats = new ArrayList<>();
    public List<Dog> dogs = new ArrayList<>();

    @Override
    public IElementHandler beginChild(String name, String[] args)
            throws ParseException, ElementHandlerException {
        switch (name) {
        case "cat":
            Cat cat = new Cat();
            cats.add(cat);
            return cat;
        case "dog":
            Dog dog = new Dog();
            dogs.add(dog);
            return dog;
        }
        return null;
    }

}
