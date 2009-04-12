package net.bodz.bas.test.types;

import net.bodz.bas.lang.err.OutOfDomainException;
import net.bodz.bas.nls.AppNLS;
import net.bodz.bas.types.util.Objects;

public class Person {

    private String  name;
    private int     age;
    private boolean sex;
    private Address location;

    public Person() {
    }

    public Person(String name, int age, boolean girl, Address location) {
        this.name = name;
        this.age = age;
        this.sex = girl;
        this.location = location;
    }

    public Person(String name, int age, boolean girl) {
        this(name, age, girl, null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0 || age > 1000)
            throw new OutOfDomainException(
                    AppNLS.getString("Person.badAge") + age); //$NON-NLS-1$
        this.age = age;
    }

    public boolean isGirl() {
        return sex;
    }

    public void setGirl(boolean girl) {
        this.sex = girl;
    }

    public Address getLocation() {
        return location;
    }

    public void setLocation(Address location) {
        this.location = location;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        if (name != null)
            hash += name.hashCode();
        hash *= age;
        if (sex)
            hash = ~hash;
        if (location != null)
            hash += location.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Person))
            return false;
        Person p = (Person) obj;
        if (age != p.age)
            return false;
        if (sex != p.sex)
            return false;
        if (!Objects.equals(name, p.name))
            return false;
        if (!Objects.equals(location, p.location))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String
                .format(
                        AppNLS.getString("Person.xml_sdss"), // //$NON-NLS-1$
                        name,
                        age,
                        sex ? AppNLS.getString("Person.girl") : AppNLS.getString("Person.boy"), location); //$NON-NLS-1$ //$NON-NLS-2$
    }

    public static final Person Tom;
    public static final Person Lucy;
    public static final Person Shecti;
    public static final Person Lenik;
    static {
        Tom = new Person(
                AppNLS.getString("Person.a"), 18, false, Address.Marks100); //$NON-NLS-1$
        Lucy = new Person(
                AppNLS.getString("Person.b"), 16, true, Address.Golf200); //$NON-NLS-1$
        Shecti = new Person(
                AppNLS.getString("Person.c"), 20, true, Address.YHLib); //$NON-NLS-1$
        Lenik = new Person(
                AppNLS.getString("Person.d"), 28, true, Address.HNHome); //$NON-NLS-1$
    }

}
