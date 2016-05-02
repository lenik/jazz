package net.bodz.bas.ctx.scope;

import org.junit.Assert;

public class _ScopeTest
        extends Assert {

    public static void main(String[] args) {
        for (Class<?> c : _Scope.fn.scopeAnnotationClasses)
            System.out.println(c);
    }

}
