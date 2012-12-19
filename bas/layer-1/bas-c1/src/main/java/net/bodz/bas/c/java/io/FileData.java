package net.bodz.bas.c.java.io;

import java.io.*;
import java.nio.charset.Charset;

public class FileData {

    public static byte[] read(File file)
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

    public static String readString(File file)
            throws IOException {
        return readString(file, "utf-8");
    }

    public static String readString(File file, String charsetName)
            throws IOException {
        Charset charset = Charset.forName(charsetName);
        return readString(file, charset);
    }

    public static String readString(File file, Charset charset)
            throws IOException {
        byte[] data = read(file);
        String text = new String(data, charset);
        return text;
    }

    public static void write(File file, byte[] data)
            throws IOException {
        write(file, data, 0, data.length);
    }

    public static void write(File file, byte[] data, int off, int len)
            throws IOException {
        if (file == null)
            throw new NullPointerException("file");
        if (data == null)
            throw new NullPointerException("data");
        try (OutputStream out = new FileOutputStream(file)) {
            out.write(data, off, len);
        }
    }

    public static void writeString(File file, String contents)
            throws IOException {
        writeString(file, contents, "utf-8");
    }

    public static void writeString(File file, String contents, String charsetName)
            throws IOException {
        Charset charset = Charset.forName(charsetName);
        writeString(file, contents, charset);
    }

    public static void writeString(File file, String contents, Charset charset)
            throws IOException {
        if (contents == null)
            throw new NullPointerException("contents");
        byte[] data = contents.getBytes(charset);
        write(file, data);
    }

    /**
     * Trim the file to the length, or extend the file, the extension part may be filled with
     * unknown bytes. If the file isn't existed, a new file may be created.
     * 
     * @param newLength
     *            New length to be set. The length should be >= <code>0</code>.
     * @return <code>false</code> If the given file object does not denote an existing, writable
     *         regular file and a new regular file of that name cannot be created, or if some other
     *         error occurs while opening or creating the file.
     * @exception IOException
     *                If an I/O error occurs
     */
    public static boolean setLength(File file, long newLength)
            throws IOException {
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(file, "rw");

            raf.setLength(newLength);
        } catch (FileNotFoundException e) {
            return false;
        } finally {
            raf.close();
        }
        return true;
    }

    public static boolean touch(File file, boolean updateLastModifiedTime)
            throws IOException {
        if (!file.exists()) {
            try (FileOutputStream out = new FileOutputStream(file)) {
            } catch (FileNotFoundException e) {
                return false;
            }
        } else {
            if (updateLastModifiedTime)
                file.setLastModified(System.currentTimeMillis());
        }
        return true;
    }

}
