package net.bodz.bas.c.java.util.regex;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class UnixStyleVarProcessorTest
        extends Assert {

    @Test
    public void test() {
        Map<String, Object> vars = new HashMap<String, Object>();
        vars.put("name", "world");
        vars.put("age", 13);
        vars.put("home 1", "where");
        vars.put("$", "dollar");
        vars.put("{", "left brace");
        vars.put("}", "right brace");

        final UnixStyleVarProcessor ve = new UnixStyleVarProcessor(vars);

        class D {
            void o(String input, String expected) {
                String actual = ve.process(input);
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("hello", "hello");
        d.o("hello, $name", "hello, world");
        d.o("hello, ${name}", "hello, world");
        d.o("${\\$}", "dollar");
        d.o("${\\{}", "left brace");
        d.o("${\\}}", "right brace");
    }

}
