package net.bodz.bas.c.string;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class StringSearch {

    public static int indexOf(String s, char[] charSet, int fromIndex) {
        int len = s.length();
        for (int i = fromIndex; i < len; i++) {
            char c = s.charAt(i);
            for (int j = 0; j < charSet.length; j++)
                if (charSet[j] == c)
                    return i;
        }
        return -1;
    }

    public static int indexOf(String s, char... charSet) {
        return indexOf(s, charSet, 0);
    }

    public static int indexOf(String s, int charType, int fromIndex) {
        int len = s.length();
        for (int i = fromIndex; i < len; i++) {
            char c = s.charAt(i);
            int type = Character.getType(c);
            if (type == charType)
                return i;
        }
        return -1;
    }

    public static int indexOf(String s, int charType) {
        return indexOf(s, charType, 0);
    }

    public static int lastIndexOf(String s, char[] charSet, int fromIndex) {
        int len = s.length();
        if (fromIndex >= len)
            fromIndex = len - 1;
        for (int i = fromIndex; i >= 0; i--) {
            char c = s.charAt(i);
            for (int j = 0; j < charSet.length; j++)
                if (charSet[j] == c)
                    return i;
        }
        return -1;
    }

    public static int lastIndexOf(String s, char[] charSet) {
        return lastIndexOf(s, charSet, s.length() - 1);
    }

    public static int lastIndexOf(String s, int charType, int fromIndex) {
        int len = s.length();
        if (fromIndex >= len)
            fromIndex = len - 1;
        for (int i = fromIndex; i >= 0; i--) {
            char c = s.charAt(i);
            int type = Character.getType(c);
            if (type == charType)
                return i;
        }
        return -1;
    }

    public static int lastIndexOf(String s, int charType) {
        return lastIndexOf(s, charType, s.length() - 1);
    }

    public static int indexOfNoncontinuous(String s, int charType, int start) {
        int lastEnd = -1;
        do {
            int end = StringSearch.indexOf(s, charType, start);
            if (end == -1)
                return lastEnd;
            if (end != start)
                return lastEnd != -1 ? lastEnd : end;
            lastEnd = end;
            start++;
        } while (true);
    }

    public static String[] splitByNoncontinuous(String s, int charType) {
        List<String> list = new ArrayList<>();
        int start = 0, next;
        while ((next = indexOfNoncontinuous(s, charType, start + 1)) != -1) {
            list.add(s.substring(start, next));
            start = next;
        }
        list.add(s.substring(start));
        return list.toArray(new String[0]);
    }

    public static int indexOfIgnoreCase(String s, String needle, Locale locale) {
        return s.toLowerCase(locale).indexOf(needle.toLowerCase(locale));
    }

    public static int indexOfIgnoreCase(String s, String needle) {
        return s.toLowerCase().indexOf(needle.toLowerCase());
    }

    public static int indexOfIgnoreCase(String s, char needle, Locale locale) {
        return s.toLowerCase(locale).indexOf(Character.toLowerCase(needle));
    }

    public static int indexOfIgnoreCase(String s, char needle) {
        return s.toLowerCase().indexOf(Character.toLowerCase(needle));
    }

    public static boolean contains(String s, char ch) {
        if (s == null)
            return false;
        char[] v = s.toCharArray();
        for (int i = 0; i < v.length; i++)
            if (v[i] == ch)
                return true;
        return false;
    }

}
