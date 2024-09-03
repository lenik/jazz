package net.bodz.bas.inject;

class Foo {

    String name;
    int age;

    public Foo(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Foo [name=" + name + ", age=" + age + "]";
    }

}

public class InjectorTester {

    public static void main(String[] args) {
        Injector inj = new Injector();
        inj.requireAll = false;
        inj.provide("age", 10);
        inj.provide("Lenik");
        Foo foo = inj.instantiate(Foo.class);
        System.out.println(foo);
    }

}
