package net.bodz.bas.sys;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import net.bodz.bas.types.util.Comparators;
import net.bodz.bas.types.util.Strings;

import org.junit.Test;

public class SystemPropertiesTest {

    @Test
    public void test() throws Exception {

    }

    static void generate() {
        Properties properties = System.getProperties();
        List<Object> keys = new ArrayList<Object>(properties.keySet());
        Collections.sort(keys, Comparators.STD);
        for (Object key : keys) {
            String name = String.valueOf(key);
            String value = properties.getProperty(name);
            for (int dot = name.indexOf('.'); dot != -1; dot = name.indexOf('.', dot)) {
                name = name.substring(0, dot) //
                        + Strings.ucfirst(name.substring(dot + 1));
            }
            if (value != null)
                value = Strings.escape(value);

            // System.getProperty(key)
            System.out.println("    /** " + key + " = " + value + " */"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            System.out.println("    public static String get" + Strings.ucfirst(name) + "() {\n" + // //$NON-NLS-1$ //$NON-NLS-2$
                    "        return System.getProperty(\"" + key + "\"); \n" + // //$NON-NLS-1$ //$NON-NLS-2$
                    "    }\n"); //$NON-NLS-1$
        }
    }

    public static void main(String[] args) {
        generate();
    }

}
