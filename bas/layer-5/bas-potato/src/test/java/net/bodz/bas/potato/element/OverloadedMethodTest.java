package net.bodz.bas.potato.element;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.potato.PotatoTypes;

public class OverloadedMethodTest
        extends Assert {

    static IType type = PotatoTypes.getInstance().loadType(OverloadedMethodTest.class);

    public String foo(int a, int b) {
        return "int,ini";
    }

    public String foo(Integer a, int b) {
        return "Integer,int";
    }

    public String foo(Number a, Integer b) {
        return "Number,Integer";
    }

    @Test
    public void test_int_int()
            throws Exception {
        IMethod foo = type.getOverloadedMethod("foo");
        Object result = foo.invoke(this, 3, 4);
        assertEquals("Integer,int", result);
    }

}
