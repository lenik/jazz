package net.bodz.bas.io;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import net.bodz.bas.io.adapter.PrintOutPrintWriter;
import net.bodz.bas.io.impl.NullPrintOut;
import net.bodz.bas.io.impl.TreeOutImpl;
import net.bodz.bas.meta.decl.Final;

public interface IPrintOut
        extends
            ICharOut {

    char[] NULL_CHAR = "(null)".toCharArray();
    char NEWLINE = '\n';

    default void print(CharSequence s) {
        try {
            if (s == null)
                write(NULL_CHAR);
            else
                write(s);
        } catch (IOException e) {
            throw new PrintException(e);
        }
    }

    default void print(String s) {
        try {
            // char[] chars = s == null ? NULL : s.toCharArray();
            // write(chars, 0, chars.length);
            if (s == null)
                write(NULL_CHAR);
            else
                write(s);
        } catch (IOException e) {
            throw new PrintException(e);
        }
    }

    default @Final void print(boolean b) {
        print(String.valueOf(b));
    }

    default void print(char c) {
        try {
            write(c);
        } catch (IOException e) {
            throw new PrintException(e);
        }
    }

    /**
     * @throws NullPointerException
     *             If <code>s</code> is <code>null</code>.
     */

    default void print(char[] s) {
        if (s == null)
            throw new NullPointerException("s");
        try {
            write(s, 0, s.length);
        } catch (IOException e) {
            throw new PrintException(e);
        }
    }

    default @Final void print(double d) {
        print(String.valueOf(d));
    }

    default @Final void print(float f) {
        print(String.valueOf(f));
    }

    default @Final void print(int i) {
        print(String.valueOf(i));
    }

    default @Final void print(long l) {
        print(String.valueOf(l));
    }

    default @Final void print(Object obj) {
        print(String.valueOf(obj));
    }

    /**
     * @note.impl buffer-optim: Since {@link Object#toString()} is expensive, to reduce method calls
     *            by buffering isn't necessary.
     * @throws NullPointerException
     *             If <code>args</code> is <code>null</code>.
     */

    default void print(Object... args) {
        for (Object arg : args)
            print(arg);
    }

    default void println() {
        try {
            write('\n');
        } catch (IOException e) {
            throw new PrintException(e);
        }
    }

    default void println(CharSequence x) {
        print(x);
        println();
    }

    default void println(String x) {
        print(x);
        println();
    }

    default @Final void println(boolean x) {
        println(String.valueOf(x));
    }

    default void println(char x) {
        print(x);
        println();
    }

    /**
     * @throws NullPointerException
     *             If <code>x</code> is <code>null</code>.
     */

    default void println(char[] x) {
        print(x);
        println();
    }

    default @Final void println(double x) {
        println(String.valueOf(x));
    }

    default @Final void println(float x) {
        println(String.valueOf(x));
    }

    default @Final void println(int x) {
        println(String.valueOf(x));
    }

    default @Final void println(long x) {
        println(String.valueOf(x));
    }

    default @Final void println(Object x) {
        println(String.valueOf(x));
    }

    /**
     * @note.impl buffer-optim: Since {@link Object#toString()} is expensive, to reduce method calls
     *            by buffering isn't necessary.
     * @throws NullPointerException
     *             If <code>args</code> is <code>null</code>.
     */

    default void println(Object... args) {
        if (args.length == 0) {
            println();
            return;
        }
        int max = args.length - 1;
        for (int i = 0; i < max; i++)
            print(args[i]);
        println(args[max]);
    }

    /**
     * @throws NullPointerException
     *             If any argument is <code>null</code>.
     */

    default @Final void printf(Locale l, String format, Object... args) {
        print(String.format(l, format, args));
    }

    /**
     * @throws NullPointerException
     *             If any argument is <code>null</code>.
     */

    default @Final void printf(String format, Object... args) {
        print(String.format(format, args));
    }

    @Override
    default @Final void flush() {
        try {
            _flushX();
        } catch (IOException e) {
            throw new PrintException(e);
        }
    }

    @Override
    default @Final void close() {
        try {
            _closeX();
        } catch (IOException e) {
            throw new PrintException(e);
        }
    }

    default void _flushX()
            throws IOException {
    }

    default void _closeX()
            throws IOException {
    }

    default ITreeOut indented() {
        return TreeOutImpl.from(this);
    }

    default PrintWriter toPrintWriter() {
        return new PrintOutPrintWriter(this);
    }

    IPrintOut NULL = new NullPrintOut();

}
