package net.bodz.bas.io.term;

import java.io.IOException;
import java.io.PrintStream;
import java.io.Writer;

import net.bodz.bas.io.CharOut;
import net.bodz.bas.io.CharOuts;
import net.bodz.bas.io.NullPrintStream;
import net.bodz.bas.io.NullWriter;

public class NullTerminal implements Terminal {

    @Override
    public CharOut getCharOut() {
        return CharOuts.nil;
    }

    @Override
    public Writer getWriter() {
        return NullWriter.getInstance();
    }

    @Override
    public PrintStream getPrintStream() {
        return NullPrintStream.getInstance();
    }

    @Override
    public void p() {
    }

    @Override
    public void n(String s) {
    }

    @Override
    public void p(String s) {
    }

    @Override
    public void t(String s) {
    }

    @Override
    public void n(Object obj) {
    }

    @Override
    public void p(Object obj) {
    }

    @Override
    public void t(Object obj) {
    }

    @Override
    public void n(Object... args) {
    }

    @Override
    public void p(Object... args) {
    }

    @Override
    public void t(Object... args) {
    }

    @Override
    public void f(String format, Object... args) {
    }

    @Override
    public void flush() throws IOException {
    }

    @Override
    public void close() throws IOException {
    }

    @Override
    public void beep() {
    }

    @Override
    public void setBackColor(int index) {
    }

    @Override
    public void setTextColor(int index) {
    }

}
