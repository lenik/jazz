package net.bodz.bas.c.java.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.List;

public class URLContent {

    /**
     * Read the contents as UTF-8 lines.
     * 
     * @param url
     *            Non-<code>null</code> URL to be opened and read.
     * @return Non-<code>null</code> array of lines.
     */
    public static List<String> readUtf8Lines(URL url)
            throws IOException {
        if (url == null)
            throw new NullPointerException("url");
        Charset utf8 = Charset.forName("utf-8");
        return readLines(url, utf8);
    }

    /**
     * Read the contents as lines with specific charset.
     * 
     * @param url
     *            Non-<code>null</code> URL to be opened and read.
     * @param charsetName
     *            The charset to be used.
     * @return Non-<code>null</code> array of lines.
     * @throws UnsupportedCharsetException
     */
    public static List<String> readLines(URL url, String charsetName)
            throws IOException {
        Charset charset = Charset.forName(charsetName);
        return readLines(url, charset);
    }

    /**
     * 
     * Read the contents as lines with specific charset.
     * 
     * @param url
     *            Non-<code>null</code> URL to be opened and read.
     * @param charsetName
     *            The charset to be used.
     * @return Non-<code>null</code> array of lines.
     */
    public static List<String> readLines(URL url, Charset charset)
            throws IOException {
        try (InputStream in = url.openStream();
                Reader r = new InputStreamReader(in, charset);
                BufferedReader br = new BufferedReader(r)) {

            List<String> lines = new ArrayList<String>(100);
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }

            return lines;
        }
    }

}
