package net.bodz.bas.c.java.io;

import java.io.*;
import java.nio.charset.Charset;

public class FileContent {

    public static byte[] readData(File file)
            throws IOException {
        if (file == null)
            throw new NullPointerException("file");

        long len = file.length();
        int alloc = len < Integer.MAX_VALUE ? (int) len : Integer.MAX_VALUE;
        ByteArrayOutputStream buf = new ByteArrayOutputStream(alloc);

        byte[] block = new byte[4096];
        try (InputStream in = new FileInputStream(file)) {
            while (true) {
                int cb = in.read(block);
                if (cb == -1)
                    break;
                buf.write(block, 0, cb);
            }
        }

        return buf.toByteArray();
    }

    public static String readUtf8(File file)
            throws IOException {
        return read(file, "utf-8");
    }

    public static String read(File file, String charsetName)
            throws IOException {
        Charset charset = Charset.forName(charsetName);
        return read(file, charset);
    }

    public static String read(File file, Charset charset)
            throws IOException {
        byte[] data = readData(file);
        String text = new String(data, charset);
        return text;
    }

    public static void createData(File file, byte[] data)
            throws IOException {
        createData(file, data, 0, data.length);
    }

    public static void createData(File file, byte[] data, int off, int len)
            throws IOException {
        if (file == null)
            throw new NullPointerException("file");
        if (data == null)
            throw new NullPointerException("data");
        try (OutputStream out = new FileOutputStream(file)) {
            out.write(data, off, len);
        }
    }

    public static void createUtf8(File file, String contents)
            throws IOException {
        create(file, contents, "utf-8");
    }

    public static void create(File file, String contents, String charsetName)
            throws IOException {
        Charset charset = Charset.forName(charsetName);
        create(file, contents, charset);
    }

    public static void create(File file, String contents, Charset charset)
            throws IOException {
        if (contents == null)
            throw new NullPointerException("contents");
        byte[] data = contents.getBytes(charset);
        createData(file, data);
    }

}
