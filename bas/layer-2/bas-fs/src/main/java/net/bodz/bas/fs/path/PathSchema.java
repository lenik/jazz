package net.bodz.bas.fs.path;

import java.net.URI;
import java.net.URL;
import java.util.Map;

public class PathSchema {

    public static void main(String[] args)
            throws Exception {
        URL url = Map.class.getResource("Map$Entry.class");
        // URL url = PathSchema.class.getResource("PathSchema.class");
        URI uri = url.toURI();
        System.out.println(uri);
        System.out.println(uri.getAuthority()); // null
        System.out.println(uri.getScheme());
        System.out.println(uri.getSchemeSpecificPart());
    }

}
