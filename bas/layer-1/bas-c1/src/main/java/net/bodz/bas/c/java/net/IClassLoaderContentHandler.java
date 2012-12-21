package net.bodz.bas.c.java.net;

import java.net.URL;

public interface IClassLoaderContentHandler {

    boolean classLoader(ClassLoader loader);

    boolean classpathURL(URL classpathURL);

}
