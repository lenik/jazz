package net.bodz.bas.c.java.io;

import java.io.PrintStream;
import java.util.Locale;

public class NullPrintStream
        extends PrintStream {

    public NullPrintStream() {
        super(NullOutputStream.getInstance());
    }

    @Override
    public void print(boolean b) {
    }

    @Override
    public void print(char c) {
    }

    @Override
    public void print(char[] s) {
    }

    @Override
    public void print(double d) {
    }

    @Override
    public void print(float f) {
    }

    @Override
    public void print(int i) {
    }

    @Override
    public void print(long l) {
    }

    @Override
    public void print(Object obj) {
    }

    @Override
    public void print(String s) {
    }

    @Override
    public PrintStream printf(Locale l, String format, Object... args) {
        return this;
    }

    @Override
    public PrintStream printf(String format, Object... args) {
        return this;
    }

    @Override
    public void println() {
    }

    @Override
    public void println(boolean x) {
    }

    @Override
    public void println(char x) {
    }

    @Override
    public void println(char[] x) {
    }

    @Override
    public void println(double x) {
    }

    @Override
    public void println(float x) {
    }

    @Override
    public void println(int x) {
    }

    @Override
    public void println(long x) {
    }

    @Override
    public void println(Object x) {
    }

    @Override
    public void println(String x) {
        super.println(x);
    }

    @Override
    public void write(byte[] buf, int off, int len) {
    }

    @Override
    public void write(int b) {
    }

    @Override
    public void close() {
    }

    @Override
    public void flush() {
    }

    static NullPrintStream nil = new NullPrintStream();

    public static NullPrintStream getInstance() {
        return nil;
    }

}
