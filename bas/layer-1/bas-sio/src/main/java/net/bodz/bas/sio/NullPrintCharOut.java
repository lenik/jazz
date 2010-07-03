package net.bodz.bas.sio;

import java.io.IOException;
import java.util.Locale;

public class NullPrintCharOut
        extends NullCharOut
        implements IPrintCharOut {

    @Override
    public void checkError(boolean reset)
            throws IOException {
    }

    @Override
    public void print(String s) {
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
    public void print(Object... args) {
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
    }

    @Override
    public void println(Object... args) {
    }

    @Override
    public void printf(Locale l, String format, Object... args) {
    }

    @Override
    public void printf(String format, Object... args) {
    }

    @Override
    public void flush(boolean strict) {
    }

    @Override
    public void flush() {
    }

    @Override
    public void close() {
    }

    private static final NullPrintCharOut instance = new NullPrintCharOut();

    public static NullPrintCharOut getInstance() {
        return instance;
    }

}
