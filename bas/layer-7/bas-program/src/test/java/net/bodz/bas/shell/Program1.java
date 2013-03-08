package net.bodz.bas.shell;

public class Program1 {

    static String arg0;

    public static void main(String[] args) {
        if (args.length >= 1)
            arg0 = args[0];
    }

    public static String getArg0() {
        return arg0;
    }

    public static void setArg0(String arg0) {
        Program1.arg0 = arg0;
    }

}
