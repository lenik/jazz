package net.bodz.bas.types.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.bodz.bas.lang.err.NotImplementedException;
import net.bodz.bas.text.interp.Interps;
import net.bodz.bas.text.interp.PatternProcessor;
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
        if (string == null)
            return null;
        if (string.length() <= 1)
            return string.toUpperCase();
        char ucfirst = Character.toUpperCase(string.charAt(0));
        return ucfirst + string.substring(1);
    }

    public static String lcfirst(String string) {
        if (string == null)
            return null;
        if (string.length() <= 1)
            return string.toUpperCase();
        char lcfirst = Character.toLowerCase(string.charAt(0));
        return lcfirst + string.substring(1);
    }

    public static String hyphenatize(String words) {
        while (words.startsWith("_"))
            words = words.substring(1);
        PatternProcessor pp = new PatternProcessor("[A-Z]+") {
            @Override
            protected void matched(String part) {
                print('-');
                print(part.toLowerCase());
            }
        };
        String s = pp.process(words);
        if (s.startsWith("-"))
            s = s.substring(1);
        return s;
    }

    public static String dehyphenatize(String hstr) {
        String[] parts = hstr.split("-");
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];
            if (part.isEmpty())
                buf.append('_');
            else {
                if (i != 0)
                    part = Strings.ucfirst(part);
                buf.append(part);
            }
        }
        return buf.toString();
    }

    public static String join(String separator, Object... strings) {
        StringBuffer buffer = null;
        for (Object s : strings) {
            if (buffer == null)
                buffer = new StringBuffer();
            else
                buffer.append(separator);
            buffer.append(s);
        }
        return buffer == null ? "" : buffer.toString();
    }

    public static String join(String separator, String... strings) {
        return join(separator, (Object[]) strings);
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

    public static String joinDot(int... values) {
        if (values == null)
            return null;
        StringBuffer buf = null;
        for (int rev : values) {
            if (buf == null)
                buf = new StringBuffer(values.length * 4);
            else
                buf.append('.');
            buf.append(rev);
        }
        return buf.toString();
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

    public static String ellipse(String s, int len, String ellipse) {
        if (s.length() <= len)
            return s;
        int elen = ellipse.length();
        if (len > elen)
            return s.substring(0, len - elen) + ellipse;
        else
            return ellipse.substring(0, len);
    }

    public static String ellipse(String s, int len) {
        return ellipse(s, len, "...");
    }

    public static String ellipse(String s, int len, String ellipse,
            String epstart, String epend) {
        int estart = 0;
        if (epstart != null)
            if ((estart = s.indexOf(epstart)) == -1)
                estart = 0;
            else
                estart += epstart.length();
        int eend = s.length();
        if (epend != null)
            if ((eend = s.lastIndexOf(epend)) == -1)
                eend = s.length();
        int trims = estart + (s.length() - eend);
        String prefix = s.substring(0, estart);
        String suffix = s.substring(eend);
        if (len > trims + ellipse.length())
            return prefix
                    + ellipse(s.substring(estart, eend), len - trims, ellipse)
                    + suffix;
        else
            return ellipse(s, len, ellipse);
    }

    private static PatternProcessor escapeProcessor;
    static {
        escapeProcessor = new PatternProcessor("[\\\\\"\'\r\n]") {
            @Override
            protected void matched(String part) {
                assert part.length() == 1;
                char c = part.charAt(0);
                print(escape(c));
            }
        };
    }

    public static String escape(char c) {
        switch (c) {
        case '\r':
            return "\\r";
        case '\n':
            return "\\n";
        case '\t':
            return "\\t";
        case '\0':
            return "\\0";

        case '\\': // followings: "\\"+c:
            return "\\\\";
        case '\"':
            return "\\\"";
        case '\'':
            return "\\\'";
        }
        return String.valueOf(c);
    }

    public static String escape(String s) {
        if (s == null)
            return s;
        return escapeProcessor.process(s);
    }

    public static String[] findAll(String s, Pattern pattern, int group) {
        Matcher m = pattern.matcher(s);
        List<String> list = new ArrayList<String>();
        while (m.find()) {
            list.add(m.group(group));
        }
        return list.toArray(Empty.Strings);
    }

    public static String[] findAll(String s, Pattern pattern) {
        return findAll(s, pattern, 0);
    }

    public static String[] findAll(String s, Pattern pattern, String replacement) {
        Matcher m = pattern.matcher(s);
        List<String> list = new ArrayList<String>();
        while (m.find()) {
            String deref = Interps.dereference(replacement, m);
            list.add(deref);
        }
        return list.toArray(Empty.Strings);
    }

}
