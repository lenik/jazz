package net.bodz.bas.c.system;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.c.string.StringEscape;
import net.bodz.bas.c.string.Strings;

public class SystemEnvironTest
        extends Assert {

    @Test
    public void test()
            throws Exception {

    }

    static void generate() {
        Map<String, String> env = System.getenv();
        List<String> keys = new ArrayList<String>(env.keySet());
        Collections.sort(keys);
        for (Object key : keys) {
            String name = String.valueOf(key);
            String value = env.get(name);
            for (int dot = name.indexOf('.'); dot != -1; dot = name.indexOf('.', dot)) {
                name = name.substring(0, dot) //
                        + Strings.ucfirst(name.substring(dot + 1));
            }
            if (value != null)
                value = StringEscape.escapeJava(value);

            // System.getProperty(key)
            System.out.println("    /** " + key + " = " + value + " */");
            System.out.println("    public static String get" + Strings.ucfirst(name) + "() {\n" + //
                    "        return System.getProperty(\"" + key + "\"); \n" + //
                    "    }\n");
        }
    }

    public static void main(String[] args) {
        generate();
    }

}
