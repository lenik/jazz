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
        vars.put("name", "world");
        vars.put("age", 13);
        vars.put("home 1", "where");
        vars.put("$", "dollar");
        vars.put("{", "left brace");
        vars.put("}", "right brace");

        final VariableExpand ve = new VariableExpand(vars);

        TestDefs.tests(new TestEval<String>() {
            public Object eval(String input) throws Throwable {
                return ve.process(input);
            }
        }, //
                EQ("hello", "hello"), //
                EQ("hello, $name", "hello, world"), //
                EQ("hello, ${name}", "hello, world"), //
                EQ("${\\$}", "dollar"), //
                EQ("${\\{}", "left brace"), //
                EQ("${\\}}", "right brace"), //
                END);
    }

}
