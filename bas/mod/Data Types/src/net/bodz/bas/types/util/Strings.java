package net.bodz.bas.types.util;

import java.util.Arrays;
import java.util.Map;

import net.bodz.bas.lang.err.NotImplementedException;
import net.bodz.bas.types.Pair;

public class Strings {

    public static final boolean equals(String a, String b) {
        if (a == null || b == null)
            return a == b;
        return a.equals(b);
    }

    public static final boolean equalsIgnoreCase(String a, String b) {
        if (a == null || b == null)
            return a == b;
        return a.equalsIgnoreCase(b);
    }

    public static String chop(String string, int chars) {
        assert string != null;
        if (string.length() < chars)
            return "";
        return string.substring(0, string.length() - chars);
    }

    public static String chop(String string) {
        return chop(string, 1);
    }

    public static String chomp(String string, String pattern) {
        assert string != null;
        assert pattern != null;
        int chars = pattern.length();
        if (string.length() < chars)
            return string;
        int len = string.length();
        if (string.substring(len - chars).equals(pattern))
            return string.substring(0, len - chars);
        return string;
    }

    public static String chomp(String string) {
        return chomp(string, "\n");
    }

    public static String ucfirst(String string) {
        assert string.length() >= 1;
        char ucfirst = Character.toUpperCase(string.charAt(0));
        return ucfirst + string.substring(1);
    }

    public static String lcfirst(String string) {
        assert string.length() >= 1;
        char lcfirst = Character.toLowerCase(string.charAt(0));
        return lcfirst + string.substring(1);
    }

    public static String join(String separator, String... strings) {
        StringBuffer buffer = null;
        for (String s : strings) {
            if (buffer == null)
                buffer = new StringBuffer();
            else
                buffer.append(separator);
            buffer.append(s);
        }
        return buffer == null ? "" : buffer.toString();
    }

    public static String join(String separator, Iterable<?> iterable) {
        StringBuffer buffer = null;
        for (Object o : iterable) {
            if (buffer == null)
                buffer = new StringBuffer();
            else
                buffer.append(separator);
            buffer.append(String.valueOf(o));
        }
        return buffer == null ? "" : buffer.toString();
    }

    public static Pair<String, String> join(String separatorKey,
            String separatorValue, Map<?, ?> map) {
        StringBuffer bufferKey = null;
        StringBuffer bufferValue = null;
        for (Object o : map.entrySet()) {
            Map.Entry<?, ?> entry = (Map.Entry<?, ?>) o;
            if (bufferKey == null) {
                bufferKey = new StringBuffer();
                bufferValue = new StringBuffer();
            } else {
                bufferKey.append(separatorKey);
                bufferValue.append(separatorValue);
            }
            bufferKey.append(String.valueOf(entry.getKey()));
            bufferValue.append(String.valueOf(entry.getValue()));
        }
        if (bufferKey == null)
            return new Pair<String, String>("", "");
        else
            return new Pair<String, String>(bufferKey.toString(), bufferValue
                    .toString());
    }

    public static Pair<String, String> join(String separator, Map<?, ?> map) {
        return join(separator, separator, map);
    }

    public static String before(String string, String pattern) {
        int pos = string.indexOf(pattern);
        if (pos == -1)
            throw new NotImplementedException(
                    "TODO: see substring-before for not-found");
        return string.substring(0, pos);
    }

    public static String before(String string, char pattern) {
        int pos = string.indexOf(pattern);
        if (pos == -1)
            throw new NotImplementedException(
                    "TODO: see substring-before for not-found");
        return string.substring(0, pos);
    }

    public static String after(String string, String pattern) {
        int pos = string.indexOf(pattern);
        if (pos == -1)
            throw new NotImplementedException(
                    "TODO: see substring-after for not-found");
        return string.substring(pos + 1);
    }

    public static String after(String string, char pattern) {
        int pos = string.indexOf(pattern);
        if (pos == -1)
            throw new NotImplementedException(
                    "TODO: see substring-after for not-found");
        return string.substring(pos + 1);
    }

    public static int count(String string, char pattern) {
        int count = 0;
        int index = 0;
        while ((index = string.indexOf(pattern, index)) != -1) {
            index++;
            count++;
        }
        return count;
    }

    public static int count(String string, String pattern) {
        int count = 0;
        int index = 0;
        while ((index = string.indexOf(pattern, index)) != -1) {
            index += pattern.length();
            count++;
        }
        return count;
    }

    public static String repeat(int count, String pattern) {
        StringBuffer buf = new StringBuffer(pattern.length() * count);
        while (--count >= 0)
            buf.append(pattern);
        return buf.toString();
    }

    public static String repeat(int count, char c) {
        char[] buf = new char[count];
        Arrays.fill(buf, c);
        return new String(buf);
    }

}
