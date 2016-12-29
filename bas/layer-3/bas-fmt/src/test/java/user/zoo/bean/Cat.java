package user.zoo.bean;

public class Cat
        extends Animal {

    private char miaoStyle;

    public Cat() {
        super();
    }

    public Cat(String name, boolean male, int color, int age, char miaoStyle) {
        super(name, male, color, age);
        this.miaoStyle = miaoStyle;
    }

    public char getMiaoStyle() {
        return miaoStyle;
    }

    public void setMiaoStyle(char miaoStyle) {
        this.miaoStyle = miaoStyle;
    }

}
