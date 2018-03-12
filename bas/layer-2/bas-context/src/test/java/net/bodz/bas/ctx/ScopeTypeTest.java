package net.bodz.bas.ctx;

import org.junit.Assert;

import net.bodz.bas.ctx.ScopeType;

public class ScopeTypeTest
        extends Assert {

    public static void main(String[] args) {
        for (Class<?> c : ScopeType.fn.scopeAnnotationClasses)
            System.out.println(c);
    }

}
