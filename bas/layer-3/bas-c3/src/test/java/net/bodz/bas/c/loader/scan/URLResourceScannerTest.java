package net.bodz.bas.c.loader.scan;

import java.net.URL;
import java.util.Map;

import org.junit.Assert;

import net.bodz.bas.c.java.util.MapDump;
import net.bodz.bas.c.string.StringPart;

public class URLResourceScannerTest
        extends Assert {

    public static void main(String[] args)
            throws Exception {
        Class<?> clazz = URLResourceScanner.class;
        URL self = clazz.getResource(clazz.getSimpleName() + ".class");

        String _parent = StringPart.beforeLast(self.toString(), '/');
        // _parent = StringPart.beforeLast(_parent, '/');
        URL parent = new URL(_parent);

        URLResourceScanner scanner = new URLResourceScanner(true);
        Map<String, URL> map = scanner.scan(parent);

        System.out.println(MapDump.dump(map));
    }

}
