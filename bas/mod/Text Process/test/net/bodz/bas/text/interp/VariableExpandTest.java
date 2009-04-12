package net.bodz.bas.text.interp;

import static net.bodz.bas.test.TestDefs.END;
import static net.bodz.bas.test.TestDefs.EQ;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.test.TestDefs;
import net.bodz.bas.test.TestEval;

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

        TestDefs.tests(new TestEval<String>() {
            public Object eval(String input) throws Throwable {
                return ve.process(input);
            }
        }, //
                EQ("hello", "hello"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("hello, $name", "hello, world"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("hello, ${name}", "hello, world"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("${\\$}", "dollar"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("${\\{}", "left brace"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("${\\}}", "right brace"), // //$NON-NLS-1$ //$NON-NLS-2$
                END);
    }

}
