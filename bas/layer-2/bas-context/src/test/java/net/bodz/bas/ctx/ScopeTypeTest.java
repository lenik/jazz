package net.bodz.bas.ctx;

import org.junit.Assert;

public class ScopeTypeTest
        extends Assert {

    public static void main(String[] args) {
        for (Class<?> c : ScopeType.fn.concreteAnnotationClasses)
            System.out.println(c);
    }

}
