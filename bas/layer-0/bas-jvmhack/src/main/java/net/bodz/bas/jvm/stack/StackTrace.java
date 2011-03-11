package net.bodz.bas.jvm.stack;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

public class StackTrace {

    public static String get() {
        try {
            throw new Exception("Stack trace");
        } catch (Exception hack) {
            StringWriter buf = new StringWriter();
            hack.printStackTrace(new PrintWriter(buf));
            return buf.toString();
        }
    }

    public static String get(Thread thread) {
        StackTraceElement[] stackTrace = thread.getStackTrace();
        StringBuilder buf = new StringBuilder(stackTrace.length * 40);
        buf.append("Stack trace: ");
        buf.append(thread);
        buf.append("\n");
        for (int i = 0; i < stackTrace.length; i++) {
            StackTraceElement e = stackTrace[i];
            buf.append("\tat ");
            buf.append(e.getClassName());
            buf.append('.');
            buf.append(e.getMethodName());
            buf.append('(');
            String fileName = e.getFileName();
            if (fileName != null) {
                buf.append(fileName);
                buf.append(": ");
                buf.append(e.getLineNumber());
            }
            buf.append(')');
            buf.append("\n");
        }
        return buf.toString();
    }

    public static void dump(PrintStream out) {
        String s = get();
        out.print(s);
    }

    public static void dump(Thread thread, PrintStream out) {
        String s = get(thread);
        out.print(s);
    }

}
