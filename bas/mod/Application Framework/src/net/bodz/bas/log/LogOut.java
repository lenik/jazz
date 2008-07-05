package net.bodz.bas.log;

import net.bodz.bas.io.CharOut;

public abstract class LogOut extends CharOut implements ILogOut {

    private String  name;

    private boolean newline = true;
    protected int   len     = 0;
    private int     prevlen = 0;

    public LogOut(String name) {
        if (name == null)
            this.name = "$" + System.identityHashCode(this);
        else
            this.name = name;
    }

    @Override
    public String toString() {
        String type = getClass().getName();
        int dot = type.lastIndexOf('.');
        if (dot != -1)
            type = type.substring(dot + 1);
        return type + "(" + name + ")";
    }

    protected String delim() {
        return null;
    }

    protected String prefix() {
        return null;
    }

    public final void lineBegin() {
        String s = prefix();
        if (s != null) {
            print(s);
            len += s.length();
        }
        newline = false;
    }

    private void lineEnd() {
        prevlen = len;
        len = 0;
        newline = true;
    }

    @Override
    public void p(Object obj) {
        if (newline)
            lineBegin();
        String s = String.valueOf(obj);
        print(s);
        len += s.length();
    }

    @Override
    public void p(Object... args) {
        if (newline)
            lineBegin();
        String delim = delim();
        int dlen = delim == null ? 0 : delim.length();
        for (int i = 0; i < args.length; i++) {
            if (delim != null && i != 0) {
                print(delim);
                len += dlen;
            }
            String s = String.valueOf(args[i]);
            print(s);
            len += s.length();
        }
    }

    @Override
    public void P(Object obj) {
        p(obj);
        println();
    }

    @Override
    public void P(Object... args) {
        p(args);
        println();
    }

    @Override
    public void pf(String format, Object... args) {
        if (newline)
            lineBegin();
        String s = String.format(format, args);
        print(s);
        len += s.length();
    }

    @Override
    public void PF(String format, Object... args) {
        pf(format, args);
        println();
    }

    @Override
    public void sig(Object obj) {
        p(obj);
        printcr();
    }

    @Override
    public void sig(Object... args) {
        p(args);
        printcr();
    }

    @Override
    public void sigf(String format, Object... args) {
        pf(format, args);
        printcr();
    }

    // printxxx

    @Override
    public void printcr() {
        int l = len;
        while (l++ < prevlen)
            print(' ');
        print('\r');
        lineEnd();
    }

    @Override
    public void println() {
        super.println();
        lineEnd();
    }

    @Override
    public void println(boolean x) {
        super.println(x);
        lineEnd();
    }

    @Override
    public void println(char x) {
        super.println(x);
        lineEnd();
    }

    @Override
    public void println(char[] x) {
        super.println(x);
        lineEnd();
    }

    @Override
    public void println(double x) {
        super.println(x);
        lineEnd();
    }

    @Override
    public void println(float x) {
        super.println(x);
        lineEnd();
    }

    @Override
    public void println(int x) {
        super.println(x);
        lineEnd();
    }

    @Override
    public void println(long x) {
        super.println(x);
        lineEnd();
    }

    @Override
    public void println(Object x) {
        super.println(x);
        lineEnd();
    }

    @Override
    public void println(String x) {
        super.println(x);
        lineEnd();
    }

}
