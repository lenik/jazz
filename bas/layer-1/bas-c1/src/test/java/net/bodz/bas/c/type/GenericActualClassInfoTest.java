package net.bodz.bas.c.type;

import static org.junit.Assert.fail;

import org.junit.Test;

import net.bodz.bas.io.Stdio;

public class GenericActualClassInfoTest {

    @Test
    public void test() {
        fail("Not yet implemented");
    }

    public static void main(String[] args)
            throws Exception {
        GenericActualClassInfo gCar = GenericActualClasses.getActualInfo(Car.class);
        // Type[] params = gCar.eval(Car.class);
        GenericActualClassInfo gBar = gCar.getSuper();
        GenericActualClassInfo gFoo = gBar.getSuper();
        System.out.println("gCar: " + gCar);
        System.out.println("gBar: " + gBar);
        System.out.println("gFoo: " + gFoo);

        System.out.println("----------------");
        gCar.dump(Stdio.cout.indented());
    }

}

interface Iface1<Iv11, Iv12> {
}

interface Iface2<Iv21, Iv22>
        extends
            Iface1<Iv22, Iv21> {
}

class Foo<Fv1, Fv2> {

    public Fv1 getFirst() {
        return null;
    }

    public Fv2 getSecond() {
        return null;
    }

}

class Bar<Bv1, Bv2, Bv3>
        extends Foo<Bv3, Bv2>
        implements
            Iface2<Bv2, Bv2> {
}

class Car
        extends Bar<Cat1, Cat2, Cat3>
        implements
            Iface1<Cat2, Cat2> {
}

class Cat1 {
}

class Cat2 {
}

class Cat3 {
}
