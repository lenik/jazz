package net.bodz.bas.t.pojo.eg.bookstore;

public class Cat
        extends Animal {

    public char miaoStyle;

    public Cat() {
        super();
    }

    public Cat(String name, boolean male, int color, int age, char miaoStyle) {
        super(name, male, color, age);
        this.miaoStyle = miaoStyle;
    }

}
