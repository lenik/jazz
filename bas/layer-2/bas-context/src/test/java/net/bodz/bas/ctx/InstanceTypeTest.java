package net.bodz.bas.ctx;

import org.junit.Assert;

public class InstanceTypeTest
        extends Assert {

    public static void main(String[] args) {
        for (Class<?> c : InstanceType.fn.concreteAnnotationClasses)
            System.out.println(c);
    }

}
