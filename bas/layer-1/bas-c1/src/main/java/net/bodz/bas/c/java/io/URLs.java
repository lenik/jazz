package net.bodz.bas.c.java.io;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class URLs {

    public static boolean equals(URL url1, URL url2) {
        if (url1 == url2)
            return true;
        if (url1 == null || url2 == null)
            return false;
        try {
            URI uri1 = url1.toURI();
            URI uri2 = url2.toURI();
            return uri1.equals(uri2);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
