package user.zoo.bean;

public class Dog
        extends Animal {

    private int barkVolume;

    public Dog() {
        super();
    }

    public Dog(String name, boolean male, int color, int age, int barkVolume) {
        super(name, male, color, age);
        this.barkVolume = barkVolume;
    }

    public int getBarkVolume() {
        return barkVolume;
    }

    public void setBarkVolume(int barkVolume) {
        this.barkVolume = barkVolume;
    }

}
