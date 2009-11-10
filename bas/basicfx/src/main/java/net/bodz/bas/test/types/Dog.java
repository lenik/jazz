package net.bodz.bas.test.types;

import net.bodz.bas.nls.AppNLS;
import net.bodz.bas.text.util.Quotable;
import net.bodz.bas.types.util.Objects;

public class Dog {

    private String name;
    private boolean drunk;

    public Dog() {
        this.name = AppNLS.getString("Dog.dogName"); //$NON-NLS-1$
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
        return out(AppNLS.getString("Dog.barkSound")); //$NON-NLS-1$
    }

    public String bark(String target) {
        out(AppNLS.getString("Dog.hi") + target + "!"); //$NON-NLS-1$ //$NON-NLS-2$
        return target;
    }

    static final Quotable spliter;
    static {
        spliter = new Quotable("'".toCharArray()); //$NON-NLS-1$
    }

    public String bark(String target, boolean lazy) {
        if (!lazy)
            return bark(target);
        String[] words = spliter.splitDequote("\\s+", target); //$NON-NLS-1$
        String abbr = ""; //$NON-NLS-1$
        for (String w : words)
            abbr += w.charAt(0);
        out(AppNLS.getString("Dog.hiAbbr") + abbr + "!"); //$NON-NLS-1$ //$NON-NLS-2$
        return abbr;
    }

    public String intro() {
        return out(AppNLS.getString("Dog.myNameIs") + name + "!"); //$NON-NLS-1$ //$NON-NLS-2$
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
        if (Objects.equals(name, dog.name))
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
        return AppNLS.getString("Dog.xDogName") + name + AppNLS.getString("Dog.xDrunkAttr") + drunk + ">"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }

}
