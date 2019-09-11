package user.zoo.bean;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.rst.ElementHandlerException;
import net.bodz.bas.fmt.rst.IElementHandler;
import net.bodz.bas.fmt.rst.RstObject;
import net.bodz.bas.fmt.rst.obj.RstSource;

@RstSource(bean = true)
public class Zoo
        extends RstObject {

    private String owner;
    private int createdYear;

    private List<Cat> cats = new ArrayList<Cat>();
    private List<Dog> dogs = new ArrayList<Dog>();

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getCreatedYear() {
        return createdYear;
    }

    public void setCreatedYear(int createdYear) {
        this.createdYear = createdYear;
    }

    public List<Cat> getCats() {
        return cats;
    }

    public void setCats(List<Cat> cats) {
        this.cats = cats;
    }

    public List<Dog> getDogs() {
        return dogs;
    }

    public void setDogs(List<Dog> dogs) {
        this.dogs = dogs;
    }

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
