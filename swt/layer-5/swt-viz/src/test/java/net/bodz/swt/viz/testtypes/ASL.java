package net.bodz.swt.viz.testtypes;

/**
 * Age/Sex/Location
 */
public class ASL {

    public int age;
    public boolean sex;
    public String location;

    public ASL(int age, boolean sex, String location) {
        super();
        this.age = age;
        this.sex = sex;
        this.location = location;
    }

    @Override
    public String toString() {
        char sexChar = sex ? 'm' : 'f';
        return age + "/" + sexChar + "/" + location;
    }

}
