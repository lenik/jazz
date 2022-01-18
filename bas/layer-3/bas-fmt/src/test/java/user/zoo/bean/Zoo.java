package user.zoo.bean;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.api.ElementHandlerException;
import net.bodz.bas.fmt.rst.AbstractRstObject;
import net.bodz.bas.fmt.rst.IRstHandler;
import net.bodz.bas.fmt.rst.obj.RstSource;
import net.bodz.bas.fmt.xml.IXmlForm;

@RstSource(bean = true)
public class Zoo
        extends AbstractRstObject
        implements
            IXmlForm {

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
