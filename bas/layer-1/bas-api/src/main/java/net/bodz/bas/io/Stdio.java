package net.bodz.bas.io;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import net.bodz.bas.io.adapter.DynamicInputStreamByteIn;
import net.bodz.bas.io.adapter.DynamicOutputStreamByteOut;
import net.bodz.bas.io.adapter.DynamicPrintStreamPrintOut;
import net.bodz.bas.io.adapter.ReaderCharIn;

public class Stdio {

    public static final ReaderCharIn cin = new ReaderCharIn(new InputStreamReader(System.in));

    public static ReaderCharIn cin(Charset charset) {
        return new ReaderCharIn(new InputStreamReader(System.in, charset));
    }

    public static ReaderCharIn cin(String charsetName)
            throws UnsupportedEncodingException {
        return new ReaderCharIn(new InputStreamReader(System.in, charsetName));
    }

    public static final DynamicPrintStreamPrintOut cout = new DynamicPrintStreamPrintOut() {
        @Override
        public PrintStream getPrintStream() {
            return System.out;
        }
    };
    public static final DynamicPrintStreamPrintOut cerr = new DynamicPrintStreamPrintOut() {
        @Override
        public PrintStream getPrintStream() {
            return System.err;
        }
    };

    public static final DynamicInputStreamByteIn bin = new DynamicInputStreamByteIn() {
        @Override
        public InputStream getInputStream() {
            return System.in;
        }
    };

    public static final DynamicOutputStreamByteOut bout = new DynamicOutputStreamByteOut() {
        @Override
        public OutputStream getOutputStream() {
            return System.out;
        }
    };

    public static final DynamicOutputStreamByteOut berr = new DynamicOutputStreamByteOut() {
        @Override
        public OutputStream getOutputStream() {
            return System.err;
        }
    };

}
