package net.bodz.bas.t.tuple;

import java.util.Collection;

import net.bodz.bas.c.system.SysProps;
import net.bodz.bas.t.pojo.IPair;

public class Split
        implements
            IPair<String, String> {

    public String a;
    public String b;

    public Split(String a, String b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public Split clone() {
        return new Split(a, b);
    }

    public Split swap() {
        String tmp = a;
        a = b;
        b = tmp;
        return this;
    }

    public Split toSwapped() {
        return new Split(b, a);
    }

    @Override
    public String getFirst() {
        return a;
    }

    @Override
    public void setFirst(String first) {
        this.a = first;
    }

    @Override
    public String getSecond() {
        return b;
    }

    @Override
    public void setSecond(String second) {
        this.b = second;
    }

    public Split define() {
        return define("");
    }

    public Split define(String defaultVal) {
        if (a == null)
            a = defaultVal;
        if (b == null)
            b = defaultVal;
        return this;
    }

    public Split trim() {
        if (a != null)
            a = a.trim();
        if (b != null)
            b = b.trim();
        return this;
    }

    public Split removeEmpty() {
        if (a != null && a.isEmpty())
            a = null;
        if (b != null && b.isEmpty())
            b = null;
        return this;
    }

    public Split removeBlank() {
        if (a != null && a.trim().isEmpty())
            a = null;
        if (b != null && b.trim().isEmpty())
            b = null;
        return this;
    }

    public Split remove(String s) {
        if (a != null && a.equals(s))
            a = null;
        if (b != null && b.equals(s))
            b = null;
        return this;
    }

    public Split remove(String s, boolean ignoreCase) {
        if (ignoreCase) {
            if (a != null && a.equalsIgnoreCase(s))
                a = null;
            if (b != null && b.equalsIgnoreCase(s))
                b = null;
        } else
            remove(s);
        return this;
    }

    public Split remove(Collection<String> strings) {
        if (a != null && strings.contains(a))
            a = null;
        if (b != null && strings.contains(b))
            b = null;
        return this;
    }

    public Split remove(Iterable<String> strings, boolean ignoreCase) {
        if (ignoreCase) {
            if (a != null)
                for (String s : strings)
                    if (a.equalsIgnoreCase(s)) {
                        a = null;
                        break;
                    }
            if (b != null)
                for (String s : strings)
                    if (b.equalsIgnoreCase(s)) {
                        b = null;
                        break;
                    }
        } else {
            if (a != null)
                for (String s : strings)
                    if (a.equals(s)) {
                        a = null;
                        break;
                    }
            if (b != null)
                for (String s : strings)
                    if (b.equals(s)) {
                        b = null;
                        break;
                    }
        }
        return this;
    }

    public Split convert(String from, String to) {
        if (from.equals(a))
            a = to;
        if (from.equals(b))
            b = to;
        return this;
    }

    public String join(char sep) {
        if (a == null)
            return b;
        if (b == null)
            return a;
        return a + sep + b;
    }

    public String join(String sep) {
        if (a == null)
            return b;
        if (b == null)
            return a;
        return a + sep + b;
    }

    public String join(char sep, String defaultVal) {
        if (defaultVal == null)
            throw new NullPointerException("defaultVal");
        String _a = a != null ? a : defaultVal;
        String _b = b != null ? b : defaultVal;
        int n = _a.length() + 1 + _b.length();
        StringBuilder sb = new StringBuilder(n);
        sb.append(_a);
        sb.append(sep);
        sb.append(_b);
        return sb.toString();
    }

    public String join(String sep, String defaultVal) {
        if (sep == null)
            throw new NullPointerException("sep");
        if (defaultVal == null)
            throw new NullPointerException("defaultVal");
        String _a = a != null ? a : defaultVal;
        String _b = b != null ? b : defaultVal;
        int n = _a.length() + sep.length() + _b.length();
        StringBuilder sb = new StringBuilder(n);
        sb.append(_a);
        sb.append(sep);
        sb.append(_b);
        return sb.toString();
    }

    public static Split shift(String s, char sep) {
        if (s == null)
            throw new NullPointerException("s");
        int pos = s.indexOf(sep);
        if (pos == -1)
            return new Split(s, null);
        else
            return new Split(s.substring(0, pos), s.substring(pos + 1));
    }

    public static Split shiftFirst(String s, String sep) {
        if (s == null)
            throw new NullPointerException("s");
        if (sep == null)
            throw new NullPointerException("sep");
        if (sep.isEmpty())
            throw new IllegalArgumentException("empty sep");
        int pos = s.indexOf(sep);
        if (pos == -1)
            return new Split(s, null);
        else
            return new Split(s.substring(0, pos), s.substring(pos + sep.length()));
    }

    public static Split skip(String s, char sep) {
        if (s == null)
            throw new NullPointerException("s");
        int pos = s.indexOf(sep);
        if (pos == -1)
            return new Split(null, s);
        else
            return new Split(s.substring(0, pos), s.substring(pos + 1));
    }

    public static Split skipFirst(String s, String sep) {
        if (s == null)
            throw new NullPointerException("s");
        if (sep == null)
            throw new NullPointerException("sep");
        if (sep.isEmpty())
            throw new IllegalArgumentException("empty sep");
        int pos = s.indexOf(sep);
        if (pos == -1)
            return new Split(null, s);
        else
            return new Split(s.substring(0, pos), s.substring(pos + sep.length()));
    }

    public static Split pop(String s, char sep) {
        if (s == null)
            throw new NullPointerException("s");
        int pos = s.lastIndexOf(sep);
        if (pos == -1)
            return new Split(null, s);
        else
            return new Split(s.substring(0, pos), s.substring(pos + 1));
    }

    public static Split popLast(String s, String sep) {
        if (s == null)
            throw new NullPointerException("s");
        if (sep == null)
            throw new NullPointerException("sep");
        if (sep.isEmpty())
            throw new IllegalArgumentException("empty sep");
        int pos = s.lastIndexOf(sep);
        if (pos == -1)
            return new Split(null, s);
        else
            return new Split(s.substring(0, pos), s.substring(pos + sep.length()));
    }

    public static Split chop(String s, char sep) {
        if (s == null)
            throw new NullPointerException("s");
        int pos = s.lastIndexOf(sep);
        if (pos == -1)
            return new Split(s, null);
        else
            return new Split(s.substring(0, pos), s.substring(pos + 1));
    }

    public static Split chopLast(String s, String sep) {
        if (s == null)
            throw new NullPointerException("s");
        if (sep == null)
            throw new NullPointerException("sep");
        if (sep.isEmpty())
            throw new IllegalArgumentException("empty sep");
        int pos = s.lastIndexOf(sep);
        if (pos == -1)
            return new Split(s, null);
        else
            return new Split(s.substring(0, pos), s.substring(pos + sep.length()));
    }

    public static Split keyValue(String s) {
        return shift(s, '=');
    }

    public static Split keyValue(String s, char sep) {
        return shift(s, sep);
    }

    public static Split dirBase(String s) {
        return popLast(s, SysProps.fileSep);
    }

    public static Split nameExtension(String s) {
        return chop(s, '.');
    }

    public static Split pathQuery(String s) {
        return shift(s, '?');
    }

    public static Split hostPort(String s) {
        return chop(s, ':');
    }

    public static Split protocolOther(String s) {
        return skipFirst(s, "://");
    }

    public static Split packageName(String s) {
        return pop(s, '.');
    }

    public static Split headDomain(String s) {
        return shift(s, '.');
    }

    public static Split majorVersion(String s) {
        return shift(s, '.');
    }

    public static Split artifactClassifier(String fileNameWoExt, String version) {
        Split split = chopLast(fileNameWoExt, "-" + version);
        if (split.b != null && split.b.startsWith("-"))
            split.b = split.b.substring(1);
        return split;
    }

}
