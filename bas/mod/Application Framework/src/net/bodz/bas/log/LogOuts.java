package net.bodz.bas.log;

import java.io.IOException;

import net.bodz.bas.io.CharOut;
import net.bodz.bas.io.CharOuts;
import net.bodz.bas.io.ICharOut;

public class LogOuts {

    public static final LogOut nil;
    static {
        nil = new LogOut("nil") {
            @Override
            public void write(char[] chars, int off, int len)
                    throws IOException {
            }

            @Override
            public void _write(char c) throws IOException {
            }

            @Override
            public void _write(CharSequence chars, int off, int len)
                    throws IOException {
            }

            @Override
            public void _write(String string, int off, int len)
                    throws IOException {
            }
        };
    }

    public static final LogOut stdout = get(CharOuts.stdout, "stdout");
    public static final LogOut stderr = get(CharOuts.stderr, "stderr");

    public static LogOut get(final ICharOut out, String name) {
        return new LogOut(name) {
            @Override
            public void write(char[] chars, int off, int len)
                    throws IOException {
                out.write(chars, off, len);
            }
        };
    }

    public static LogOut get(final CharOut out, String name) {
        return new Proxy(out, name);
    }

    public static class Proxy extends LogOut {

        protected final CharOut proxy;

        public Proxy(CharOut proxy, String name) {
            super(name);
            assert proxy != null;
            this.proxy = proxy;
        }

        @Override
        public void write(char[] chars, int off, int len) throws IOException {
            proxy.write(chars, off, len);
        }

        @Override
        public void _write(char c) throws IOException {
            proxy._write(c);
        }

        @Override
        public void _write(CharSequence chars, int off, int len)
                throws IOException {
            proxy._write(chars, off, len);
        }

        @Override
        public void _write(String string, int off, int len) throws IOException {
            proxy._write(string, off, len);
        }

    }

}
