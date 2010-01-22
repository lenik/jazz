package net.bodz.bas.sysutil.process;

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
            Field field = clazz.getDeclaredField(name);
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
                return unwrap((InputStream) BufferedInputStream_in.get(in));
            if (in instanceof ReaderInputStream)
                return unwrap((InputStream) InputStreamReader_in.get(in));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return in;
    }

    public static OutputStream unwrap(OutputStream out) {
        try {
            if (out instanceof BufferedOutputStream)
                return unwrap((OutputStream) BufferedOutputStream_out.get(out));
            if (out instanceof WriterOutputStream)
                return unwrap((OutputStream) OutputStreamWriter_out.get(out));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return out;
    }

    public static Reader unwrap(Reader reader) {
        try {
            if (reader instanceof BufferedReader)
                return unwrap((Reader) BufferedReader_reader.get(reader));
            if (reader instanceof InputStreamReader)
                return unwrap((Reader) ReaderInputStream_reader.get(reader));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return reader;
    }

    public static Writer unwrap(Writer writer) {
        try {
            if (writer instanceof BufferedWriter)
                return unwrap((Writer) BufferedWriter_writer.get(writer));
            if (writer instanceof OutputStreamWriter)
                return unwrap((Writer) WriterOutputStream_writer.get(writer));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return writer;
    }

}
