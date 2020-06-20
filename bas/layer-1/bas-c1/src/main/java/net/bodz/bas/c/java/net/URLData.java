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

import net.bodz.bas.c.java.nio.Charsets;

public class URLData {

    /**
     * Read the contents as UTF-8 lines.
     * 
     * @param url
     *            Non-<code>null</code> URL to be opened and read.
     * @return Non-<code>null</code> array of lines.
     */
    public static String readTextContents(URL url)
            throws IOException {
        if (url == null)
            throw new NullPointerException("url");
        return readTextContents(url, Charsets.UTF8);
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
    public static String readTextContents(URL url, String charsetName)
            throws IOException {
        Charset charset = Charset.forName(charsetName);
        return readTextContents(url, charset);
    }

    /**
     * 
     * Read the contents with specific charset.
     * 
     * @param url
     *            Non-<code>null</code> URL to be opened and read.
     * @param charset
     *            The charset to be used.
     * @return Non-<code>null</code> text contents.
     */
    public static String readTextContents(URL url, Charset charset)
            throws IOException {
        StringBuilder buf = new StringBuilder();
        InputStream in = url.openStream();
        try {
            Reader r = new InputStreamReader(in, charset);
            try {
                char block[] = new char[4096];
                int cc;
                while ((cc = r.read(block)) != -1) {
                    buf.append(block, 0, cc);
                }
            } finally {
                r.close();
            }
        } finally {
            in.close();
        }
        return buf.toString();
    }

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
        return readLines(url, Charsets.UTF8);
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
        InputStream in = url.openStream();
        try {
            Reader r = new InputStreamReader(in, charset);
            BufferedReader br = new BufferedReader(r);

            List<String> lines = new ArrayList<String>(100);
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }

            return lines;
        } finally {
            in.close();
        }
    }
}
