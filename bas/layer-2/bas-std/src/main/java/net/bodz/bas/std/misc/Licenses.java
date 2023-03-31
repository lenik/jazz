package net.bodz.bas.std.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import net.bodz.bas.c.java.nio.Charsets;

public class Licenses {

    public static String get(String licenseName) {
        String simpleName = Licenses.class.getSimpleName();
        URL resource = Licenses.class.getResource(simpleName + "." + licenseName);
        if (resource == null)
            throw new IllegalArgumentException("No such license: " + licenseName);

        InputStream in = null;
        try {
            in = resource.openStream();
            InputStreamReader reader = new InputStreamReader(in, Charsets.UTF_8);
            BufferedReader lineReader = new BufferedReader(reader);

            StringBuilder buf = new StringBuilder(4096);
            String line;
            while ((line = lineReader.readLine()) != null) {
                buf.append(line);
                buf.append('\n');
            }

            String license = buf.toString();
            return license;

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            if (in != null)
                try {
                    in.close();
                } catch (IOException e) {
                }
        }
    }

    public static final String GPLv2 = get("GPLv2");

}
