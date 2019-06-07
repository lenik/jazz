package user.zoo.bean;

public class Animal {

    private String name;
    private boolean male;
    private int color;
    private int age;

    public Animal() {
    }

    public Animal(String name, boolean male, int color, int age) {
        this();
        this.name = name;
        this.male = male;
        this.color = color;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
