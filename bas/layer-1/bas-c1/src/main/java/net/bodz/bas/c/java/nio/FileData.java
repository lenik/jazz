package net.bodz.bas.c.java.nio;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.Instant;

public class FileData {

    public static byte[] read(Path file)
            throws IOException {
        if (file == null)
            throw new NullPointerException("file");

        long len = Files.size(file);
        int alloc = len < Integer.MAX_VALUE ? (int) len : Integer.MAX_VALUE;
        ByteArrayOutputStream buf = new ByteArrayOutputStream(alloc);

        byte[] block = new byte[4096];
        InputStream in = Files.newInputStream(file);
        try {
            while (true) {
                int cb = in.read(block);
                if (cb == -1)
                    break;
                buf.write(block, 0, cb);
            }
        } finally {
            in.close();
        }

        return buf.toByteArray();
    }

    public static String readString(Path file)
            throws IOException {
        return readString(file, "utf-8");
    }

    public static String readString(Path file, String charsetName)
            throws IOException {
        Charset charset = Charset.forName(charsetName);
        return readString(file, charset);
    }

    public static String readString(Path file, Charset charset)
            throws IOException {
        byte[] data = read(file);
        String text = new String(data, charset);
        return text;
    }

    public static void write(Path file, byte[] data)
            throws IOException {
        write(file, data, 0, data.length);
    }

    public static void write(Path file, byte[] data, int off, int len)
            throws IOException {
        if (file == null)
            throw new NullPointerException("file");
        if (data == null)
            throw new NullPointerException("data");
        OutputStream out = Files.newOutputStream(file);
        try {
            out.write(data, off, len);
        } finally {
            out.close();
        }
    }

    public static void writeString(Path file, String contents)
            throws IOException {
        writeString(file, contents, "utf-8");
    }

    public static void writeString(Path file, String contents, String charsetName)
            throws IOException {
        Charset charset = Charset.forName(charsetName);
        writeString(file, contents, charset);
    }

    public static void writeString(Path file, String contents, Charset charset)
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
    public static boolean setLength(Path file, long newLength)
            throws IOException {
        try (SeekableByteChannel ch = Files.newByteChannel(file)) {
            ch.truncate(newLength);
        }
        return true;
    }

    public static boolean touch(Path file, boolean updateLastModifiedTime)
            throws IOException {
        if (Files.notExists(file)) {
            try (OutputStream out = Files.newOutputStream(file)) {
                // just create it.
            } catch (FileNotFoundException e) {
                return false;
            }
        } else {
            if (updateLastModifiedTime)
                Files.setLastModifiedTime(file, FileTime.from(Instant.now()));
        }
        return true;
    }
}
