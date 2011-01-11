package net.bodz.bas.c1.examples;

import net.bodz.bas.c1.util.Nullables;

public class Dog {

    private String name;
    private boolean drunk;

    public Dog() {
        this.name = "Little White";
    }

    public Dog(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    protected String out(String s) {
        System.out.println(s);
        return s;
    }

    public String bark() {
        return out("Wang wang!!");
    }

    public String bark(String target) {
        out("Hi, " + target + "!");
        return target;
    }

    public String bark(String target, boolean lazy) {
        if (!lazy)
            return bark(target);
        String[] words = target.split("\\s+");
        String abbr = "";
        for (String w : words)
            abbr += w.charAt(0);
        out("H, " + abbr + "!");
        return abbr;
    }

    public String intro() {
        return out("My name is " + name + "!");
    }

    public void drink() {
        drunk = true;
    }

    public int calc(int x, int y) {
        if (drunk)
            return x - y;
        else
            return x + y;
    }

    public Dog makeLove(Dog friend, String babyName) {
        return new Dog(babyName);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Dog))
            return false;
        Dog dog = (Dog) obj;
        if (drunk != dog.drunk)
            return false;
        if (Nullables.equals(name, dog.name))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        if (name != null)
            hash = name.hashCode();
        if (drunk)
            hash = ~hash;
        return hash;
    }

    @Override
    public String toString() {
        return "<Dog name=" + name + " drunk=" + drunk + ">";
    }

}
