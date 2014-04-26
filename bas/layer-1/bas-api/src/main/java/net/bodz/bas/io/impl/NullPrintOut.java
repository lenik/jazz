package net.bodz.bas.io.impl;

import java.io.PrintWriter;
import java.util.Locale;

import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.io.adapter.PrintOutPrintWriter;

public class NullPrintOut
        extends NullCharOut
        implements IPrintOut {

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

    @Override
    public PrintWriter toPrintWriter() {
        return new PrintOutPrintWriter(this);
    }

}
