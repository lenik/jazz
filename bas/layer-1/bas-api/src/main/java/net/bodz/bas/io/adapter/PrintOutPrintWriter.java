package net.bodz.bas.io.adapter;

import java.io.PrintWriter;

import net.bodz.bas.io.IPrintOut;

public class PrintOutPrintWriter
        extends PrintWriter {

    private final IPrintOut out;

    /**
     * @throws NullPointerException
     *             If <code>charOut</code> is <code>null</code>.
     */
    public PrintOutPrintWriter(IPrintOut out) {
        super(new CharOutWriter(out));
        this.out = out;
    }

    @Override
    public void print(String s) {
        out.print(s);
    }

    @Override
    public void print(boolean b) {
        out.print(b);
    }

    @Override
    public void print(char c) {
        out.print(c);
    }

    @Override
    public void print(char[] s) {
        out.print(s);
    }

    @Override
    public void print(double d) {
        out.print(d);
    }

    @Override
    public void print(float f) {
        out.print(f);
    }

    @Override
    public void print(int i) {
        out.print(i);
    }

    @Override
    public void print(long l) {
        out.print(l);
    }

    @Override
    public void print(Object obj) {
        out.print(obj);
    }

    @Override
    public void println() {
        out.println();
    }

    @Override
    public void println(boolean x) {
        out.println(x);
    }

    @Override
    public void println(char x) {
        out.println(x);
    }

    @Override
    public void println(char[] x) {
        out.println(x);
    }

    @Override
    public void println(double x) {
        out.println(x);
    }

    @Override
    public void println(float x) {
        out.println(x);
    }

    @Override
    public void println(int x) {
        out.println(x);
    }

    @Override
    public void println(long x) {
        out.println(x);
    }

    @Override
    public void println(Object x) {
        out.println(x);
    }

    @Override
    public void println(String x) {
        out.println(x);
    }

}
