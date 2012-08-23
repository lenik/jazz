package net.bodz.swt.viz.testtypes;

import net.bodz.bas.gui.a.Label;

public class SimplePerson {

    public String name;

    protected int age;

    protected boolean sex;

    public SimplePerson() {
    }

    public SimplePerson(String name, int age, boolean sex) {
        super();
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    @Label("&Age")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isMale() {
        return sex;
    }

    @Override
    public String toString() {
        String title = sex ? "Mr. " : "Ms. ";
        return title + name + " is " + age + " years old. ";
    }

}