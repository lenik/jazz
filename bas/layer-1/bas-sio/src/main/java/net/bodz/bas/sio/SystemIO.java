package net.bodz.bas.sio;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class SystemIO {

    public static final ReaderCharIn cin = new ReaderCharIn(new InputStreamReader(System.in));

    public static ReaderCharIn cin(Charset charset) {
        return new ReaderCharIn(new InputStreamReader(System.in, charset));
    }

    public static ReaderCharIn cin(String charsetName)
            throws UnsupportedEncodingException {
        return new ReaderCharIn(new InputStreamReader(System.in, charsetName));
    }

    public static final DynamicPrintStreamCharOut cout = new DynamicPrintStreamCharOut() {
        @Override
        public PrintStream getPrintStream() {
            return System.out;
        }
    };
    public static final DynamicPrintStreamCharOut cerr = new DynamicPrintStreamCharOut() {
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
