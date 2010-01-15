package net.bodz.bas.c1;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@interface A {
}

@A
class X {

    @A
    public void f() {
    }

}

class Y
        extends X {

    @Override
    public void f() {
        super.f();
    }

}

public class InheritableAnnotation {

    public static void main(String[] args)
            throws Exception {
        System.out.println("X/A: " + X.class.getAnnotation(A.class));
        System.out.println("Y/A: " + Y.class.getAnnotation(A.class));

        Method X_f = X.class.getMethod("f");
        Method Y_f = Y.class.getMethod("f");
        System.out.println("X_f/a: " + X_f.getAnnotation(A.class));
        System.out.println("Y_f/a: " + Y_f.getAnnotation(A.class));
    }

}
