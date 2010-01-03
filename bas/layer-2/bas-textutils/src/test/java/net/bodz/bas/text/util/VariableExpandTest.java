package net.bodz.bas.text.util;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class VariableExpandTest {

    @Test
    public void test() {
        Map<String, Object> vars = new HashMap<String, Object>();
        vars.put("name", "world"); //$NON-NLS-1$ //$NON-NLS-2$
        vars.put("age", 13); //$NON-NLS-1$
        vars.put("home 1", "where"); //$NON-NLS-1$ //$NON-NLS-2$
        vars.put("$", "dollar"); //$NON-NLS-1$ //$NON-NLS-2$
        vars.put("{", "left brace"); //$NON-NLS-1$ //$NON-NLS-2$
        vars.put("}", "right brace"); //$NON-NLS-1$ //$NON-NLS-2$

        final VariableExpand ve = new VariableExpand(vars);

        class D {
            void o(String input, String expected) {
                String actual = ve.process(input);
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("hello", "hello"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("hello, $name", "hello, world"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("hello, ${name}", "hello, world"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("${\\$}", "dollar"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("${\\{}", "left brace"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("${\\}}", "right brace"); //$NON-NLS-1$ //$NON-NLS-2$
    }

}
