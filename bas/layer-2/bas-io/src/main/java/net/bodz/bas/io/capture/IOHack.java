package net.bodz.bas.io.capture;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Field;

import net.bodz.bas.io.ReaderInputStream;
import net.bodz.bas.io.WriterOutputStream;
import net.bodz.bas.jdk6compat.jdk7emul.IllegalAccessException;
import net.bodz.bas.jdk6compat.jdk7emul.Jdk7Reflect;
import net.bodz.bas.jdk6compat.jdk7emul.NoSuchFieldException;

public class IOHack {

    private static final Field BufferedInputStream_in;
    private static final Field BufferedOutputStream_out;
    private static final Field InputStreamReader_in;
    private static final Field OutputStreamWriter_out;

    private static final Field BufferedReader_reader;
    private static final Field BufferedWriter_writer;
    private static final Field ReaderInputStream_reader;
    private static final Field WriterOutputStream_writer;

    // ReaderInputStream_reader

    static Field hackField(Class<?> clazz, String name) {
        try {
            Field field = Jdk7Reflect.getDeclaredField(clazz, name);
            field.setAccessible(true);
            return field;
        } catch (NoSuchFieldException e) {
            throw new Error(e.getMessage(), e);
        }
    }

    static {
        BufferedInputStream_in = hackField(BufferedInputStream.class, "in");
        BufferedOutputStream_out = hackField(BufferedOutputStream.class, "out");
        InputStreamReader_in = hackField(InputStreamReader.class, "in");
        OutputStreamWriter_out = hackField(OutputStreamWriter.class, "out");
        BufferedReader_reader = hackField(BufferedReader.class, "reader");
        BufferedWriter_writer = hackField(BufferedWriter.class, "writer");
        ReaderInputStream_reader = hackField(ReaderInputStream.class, "reader");
        WriterOutputStream_writer = hackField(WriterOutputStream.class, "writer");
    }

    public static InputStream unwrap(InputStream in) {
        try {
            if (in instanceof BufferedInputStream)
                return unwrap((InputStream) Jdk7Reflect.get(BufferedInputStream_in, in));
            if (in instanceof ReaderInputStream)
                return unwrap((InputStream) Jdk7Reflect.get(InputStreamReader_in, in));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return in;
    }

    public static OutputStream unwrap(OutputStream out) {
        try {
            if (out instanceof BufferedOutputStream)
                return unwrap((OutputStream) Jdk7Reflect.get(BufferedOutputStream_out, out));
            if (out instanceof WriterOutputStream)
                return unwrap((OutputStream) Jdk7Reflect.get(OutputStreamWriter_out, out));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return out;
    }

    public static Reader unwrap(Reader reader) {
        try {
            if (reader instanceof BufferedReader)
                return unwrap((Reader) Jdk7Reflect.get(BufferedReader_reader, reader));
            if (reader instanceof InputStreamReader)
                return unwrap((Reader) Jdk7Reflect.get(ReaderInputStream_reader, reader));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return reader;
    }

    public static Writer unwrap(Writer writer) {
        try {
            if (writer instanceof BufferedWriter)
                return unwrap((Writer) Jdk7Reflect.get(BufferedWriter_writer, writer));
            if (writer instanceof OutputStreamWriter)
                return unwrap((Writer) Jdk7Reflect.get(WriterOutputStream_writer, writer));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return writer;
    }

}
