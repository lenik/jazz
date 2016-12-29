package user.zoo.reflect;

public class Dog
        extends Animal {

    public int barkVolume;

    public Dog() {
        super();
    }

    public Dog(String name, boolean male, int color, int age, int barkVolume) {
        super(name, male, color, age);
        this.barkVolume = barkVolume;
    }

}
