package net.bodz.bas.t.record;

import net.bodz.bas.meta.decl.NotNull;

public class Dog
        implements IKeyed<String>,
                   IColumnChangeSource {

    String name;
    String color;
    int age;

    private final ColumnChangeSupport ccs = new ColumnChangeSupport(this);

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
        String old = this.name;
        this.name = name;
        ccs.fireColumnChange(NAME, old, name);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        String old = this.color;
        this.color = color;
        ccs.fireColumnChange(COLOR, old, color);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        int old = this.age;
        this.age = age;
        ccs.fireColumnChange(AGE, old, age);
    }

    @Override
    public String toString() {
        return name + "/" + age + "/" + color;
    }

    @Override
    public void addColumnChangeListener(@NotNull IColumnChangeListener listener) {
        ccs.addColumnChangeListener(listener);
    }

    @Override
    public void addColumnChangeListener(IColumnType<?, ?> column, @NotNull IColumnChangeListener listener) {
        ccs.addColumnChangeListener(column, listener);
    }

    @Override
    public void removeColumnChangeListener(@NotNull IColumnChangeListener listener) {
        ccs.removeColumnChangeListener(listener);
    }

    @Override
    public void removeColumnChangeListener(IColumnType<?, ?> column, @NotNull IColumnChangeListener listener) {
        ccs.removeColumnChangeListener(column, listener);
    }

    public static final IColumnType<Dog, String> NAME = BasicColumnType.ofProperty(Dog.class, "name");
    public static final IColumnType<Dog, String> COLOR = BasicColumnType.ofProperty(Dog.class, "color");
    public static final IColumnType<Dog, Integer> AGE = BasicColumnType.ofProperty(Dog.class, "age");
    public static final IKeyedRecordType<Dog, String> TYPE = KeyedRecordType.ofList(NAME, COLOR, AGE);

    public static void main(String[] args) {
        Dog dog1 = new Dog("a", "white", 3);
        Dog dog2 = new Dog("b", "red", 3);
        Dog dog3 = new Dog("c", "white", 4);

        IKeyedRecordMap<String, Dog> dogs = new KeyedBoundRecordMap<String, Dog>(TYPE);
        dogs.insert(dog1);
        dogs.insert(dog2);
        dogs.insert(dog3);

        System.out.println(dogs.toString());
        System.out.println("whites: " + dogs.find(COLOR, "white"::equals));
        System.out.println("3-years: " + dogs.find(AGE, a -> a == 3));

        dog1.setColor("pink");
        dog2.setColor("white");
        dog1.setAge(6);
        dog3.setAge(3);

        System.out.println("some properties have been changed, and");
        System.out.println(dogs.toString());
        System.out.println("whites: " + dogs.find(COLOR, "white"::equals));
        System.out.println("3-years: " + dogs.find(AGE, a -> a == 3));

        dogs.removeAll(dogs.find(COLOR, "white"::equals));
        System.out.println("No more white: " + dogs.toString());
    }

}
