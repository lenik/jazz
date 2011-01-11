package net.bodz.bas.sysinfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;
import net.bodz.bas.regex.StringQuote;
import net.bodz.bas.text.util.Strings;

import org.junit.Test;

public class SystemEnvironTest
        extends TestCase {

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
                value = StringQuote.escape(value);

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
