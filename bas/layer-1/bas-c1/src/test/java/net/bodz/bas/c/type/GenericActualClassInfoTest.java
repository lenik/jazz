package net.bodz.bas.c.type;

import static org.junit.Assert.fail;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;

import org.junit.Test;

import net.bodz.bas.io.Stdio;

public class GenericActualClassInfoTest {

    @Test
    public void test() {
        fail("Not yet implemented");
    }

    public static void main(String[] args)
            throws Exception {
        GenericActualClassInfo gCar = GenericTypes.getActualInfo(Car.class);
        // Type[] params = gCar.eval(Car.class);
        GenericActualClassInfo gBar = gCar.getSuper();
        GenericActualClassInfo gFoo = gBar.getSuper();
        System.out.println("gCar: " + gCar);
        System.out.println("gBar: " + gBar);
        System.out.println("gFoo: " + gFoo);

        GenericActualClassInfo g1 = gCar.getInterface(Iface1.class);
        System.out.println(Arrays.asList(g1.getBounds()));

        System.out.println("----------------");
        gCar.dump(Stdio.cout.indented());

        TypeVariable<?>[] mbvars = ManyBounds.class.getTypeParameters();
        Type mbsup = ManyBounds.class.getGenericSuperclass();
        Type[] mbargs = ((ParameterizedType) mbsup).getActualTypeArguments();
        System.out.println("Bounding of " + ManyBounds.class);
        for (TypeVariable<?> var : mbvars) {
            Type[] bounds = var.getBounds();
            System.out.println("    bounds of " + var);
            for (Type b : bounds) {
                System.out.println("        bound " + b);
            }
        }

        GenericActualClassInfo gMany = GenericTypes.getActualInfo(ManyBounds.class);
        GenericActualClassInfo gManySuper = gMany.getSuper();
        System.out.println(Arrays.asList(gManySuper.getBounds()));
    } // main

}

class ManyBounds<M extends Cat1 & Iface1<J, ? super K>, J, K extends J>
        extends Foo<M, J> {
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
