package net.bodz.bas.t.record;

public class Dog
        implements IKeyed<String> {

    String name;
    String color;
    int age;

    public Dog() {
    }

    public Dog(String name, String color, int age) {
        this.name = name;
        this.color = color;
        this.age = age;
    }

    @Override
    public String getKey() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return name;
    }

    public static final IColumnType<Dog, String> NAME = BeanColumnType.ofProperty(Dog::getName, Dog::setName);
    public static final IColumnType<Dog, String> COLOR = BeanColumnType.ofProperty(Dog::getColor, Dog::setColor);
    public static final IColumnType<Dog, Integer> AGE = BeanColumnType.ofProperty(Dog::getAge, Dog::setAge);
    public static final IKeyedRecordType<Dog, String> TYPE = KeyedBeanRecordType.ofList(NAME, COLOR, AGE);

    public static void main(String[] args) {
        Dog dog1 = new Dog("a", "white", 3);
        Dog dog2 = new Dog("b", "red", 3);
        Dog dog3 = new Dog("c", "white", 4);

        KeyedRecordMap<Dog, String> dogs = new KeyedRecordMap<>(TYPE);
        dogs.insert(dog1);
        dogs.insert(dog2);
        dogs.insert(dog3);
        System.out.println(dogs.toString());

        System.out.println("whites: " + dogs.findEquals(COLOR, "white"));
        System.out.println("4-years: " + dogs.findEquals(AGE, 4));

        dogs.removeAll(dogs.findEquals(COLOR, "white"));
        System.out.println(dogs.toString());
    }

}
