package net.bodz.bas.sysinfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import net.bodz.bas.text.util.StringPrep;
import net.bodz.bas.text.util.Strings;

import org.junit.Test;

public class SystemPropertiesTest {

    @Test
    public void test()
            throws Exception {

    }

    static void generate() {
        Properties properties = System.getProperties();
        List<String> keys = new ArrayList<String>(properties.stringPropertyNames());
        Collections.sort(keys);
        for (Object key : keys) {
            String name = String.valueOf(key);
            String value = properties.getProperty(name);
            for (int dot = name.indexOf('.'); dot != -1; dot = name.indexOf('.', dot)) {
                name = name.substring(0, dot) //
                        + Strings.ucfirst(name.substring(dot + 1));
            }
            if (value != null)
                value = StringPrep.escape(value);

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
