package net.bodz.bas.c.string;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import net.bodz.bas.t.pojo.Pair;

public class StringArray {

    public static String join(String separator, Object array) {
        if (array == null)
            throw new NullPointerException("array");
        if (!array.getClass().isArray())
            throw new IllegalArgumentException("Not an array: " + array.getClass());
        StringBuilder buffer = null;
        int len = Array.getLength(array);
        for (int i = 0; i < len; i++) {
            if (buffer == null)
                buffer = new StringBuilder();
            else
                buffer.append(separator);
            Object e = Array.get(array, i);
            buffer.append(e);
        }
        return buffer == null ? "" : buffer.toString();
    }

    public static String join(String separator, Iterable<?> iterable) {
        StringBuilder buffer = null;
        for (Object o : iterable) {
            if (buffer == null)
                buffer = new StringBuilder();
            else
                buffer.append(separator);
            buffer.append(String.valueOf(o));
        }
        return buffer == null ? "" : buffer.toString();
    }

    public static String join(String separator, Enumeration<?> enumr) {
        StringBuilder buffer = null;
        while (enumr.hasMoreElements()) {
            Object o = enumr.nextElement();
            if (buffer == null)
                buffer = new StringBuilder();
            else
                buffer.append(separator);
            buffer.append(String.valueOf(o));
        }
        return buffer == null ? "" : buffer.toString();

    }

    public static String joinReversed(String separator, List<?> list) {
        if (list == null)
            throw new NullPointerException("list");
        StringBuilder buf = new StringBuilder();
        int max = list.size() - 1;
        for (int index = max; index >= 0; index--) {
            if (index != max)
                buf.append(separator);
            Object val = list.get(index);
            buf.append(val);
        }
        return buf.toString();
    }

    public static String join(Map<?, ?> map) {
        return join(" = ", "\n", map);
    }

    /**
     * @param delimitor
     *            String between the key and the value.
     * @param separator
     *            String between each entry.
     * @param map
     *            Non-<code>null</code> {@link Map map}.
     * @return String contains all the entries in the map.
     */
    public static String join(String delimitor, String separator, Map<?, ?> map) {
        if (map == null)
            throw new NullPointerException("map");
        StringBuilder buf = null;
        for (Entry<?, ?> entry : map.entrySet()) {
            if (buf == null)
                buf = new StringBuilder(map.size() * 40);
            else
                buf.append(separator);
            buf.append(entry.getKey());
            buf.append(delimitor);
            buf.append(entry.getValue());
        }
        if (buf == null)
            return "";
        else
            return buf.toString();
    }

    public static Pair<String, String> join2(String separatorKey, String separatorValue, Map<?, ?> map) {
        StringBuilder bufferKey = null;
        StringBuilder bufferValue = null;
        for (Entry<?, ?> entry : map.entrySet()) {
            if (bufferKey == null) {
                bufferKey = new StringBuilder();
                bufferValue = new StringBuilder();
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
            return new Pair<String, String>(bufferKey.toString(), bufferValue.toString());
    }

    public static Pair<String, String> join2(String separator, Map<?, ?> map) {
        return join2(separator, separator, map);
    }

    public static String joinByDot(int... values) {
        if (values == null)
            return null;
        StringBuilder buf = null;
        for (int val : values) {
            if (buf == null)
                buf = new StringBuilder(values.length * 4);
            else
                buf.append('.');
            buf.append(val);
        }
        if (buf == null)
            return "";
        return buf.toString();
    }

    public static String joinByDot(long... values) {
        if (values == null)
            return null;
        StringBuilder buf = null;
        for (long val : values) {
            if (buf == null)
                buf = new StringBuilder(values.length * 4);
            else
                buf.append('.');
            buf.append(val);
        }
        if (buf == null)
            return "";
        return buf.toString();
    }

    public static List<String> splitTokens(String string, String delims) {
        return splitTokens(string, delims, -1);
    }

    public static List<String> splitTokens(String string, String delims, int limit) {
        StringTokenizer tokens = new StringTokenizer(string, delims);
        List<String> list = new ArrayList<>();
        while (tokens.hasMoreTokens()) {
            if (limit != -1 && list.size() >= limit)
                break;
            String token = tokens.nextToken();
            list.add(token);
        }
        return list;
    }

    public static String[] splitRaw(String string, char separator) {
        return splitRaw(string, separator, -1);
    }

    public static String[] splitRaw(String string, char separator, int limit) {
        if (string == null)
            throw new NullPointerException("src");
        List<String> list = new ArrayList<String>();
        int offset = 0;
        while (limit != 0) {
            if (limit == 1) {
                list.add(string.substring(offset));
                break;
            }
            int pos = string.indexOf(separator, offset);
            if (pos == -1) {
                list.add(string.substring(offset));
                break;
            } else {
                list.add(string.substring(offset, pos));
                offset = pos + 1;
            }
            if (limit > 0)
                limit--;
        }
        return list.toArray(new String[0]);
    }

    public static String[] splitRaw(String string, String separator) {
        return splitRaw(string, separator, -1);
    }

    public static String[] splitRaw(String string, String separator, int limit) {
        if (string == null)
            throw new NullPointerException("src");
        if (separator == null)
            throw new NullPointerException("separator");

        int nsep = separator.length();
        if (nsep == 0)
            throw new IllegalArgumentException("separator is empty");

        List<String> list = new ArrayList<String>();
        int offset = 0;
        while (limit != 0) {
            if (limit == 1) {
                list.add(string.substring(offset));
                break;
            }
            int pos = string.indexOf(separator, offset);
            if (pos == -1) {
                list.add(string.substring(offset));
                break;
            } else {
                list.add(string.substring(offset, pos));
                offset = pos + nsep;
            }
            if (limit > 0)
                limit--;
        }
        return list.toArray(new String[0]);
    }

    public static String[] splitBySize(String s, int[] sizes, int limit) {
        if (limit == 0)
            limit = Integer.MAX_VALUE;
        if (sizes.length < 1)
            throw new IllegalArgumentException("empty sizes");
        int len = s.length();
        int _sizesum = 0;
        for (int i = 0; i < sizes.length; i++) {
            int size = sizes[i];
            if (size <= 0)
                throw new IllegalArgumentException("illegal size [" + i + "]");
            _sizesum += size;
        }
        List<String> list = new ArrayList<String>(len / _sizesum * sizes.length + 1);
        int start = 0;
        int index = 0;
        while (start < len) {
            if (index == limit - 1) {
                s = s.substring(start);
                assert !s.isEmpty();
                list.add(s);
                break;
            }
            int size = sizes[index++ % sizes.length];
            int subsize = Math.min(len - start, size);
            assert subsize != 0;
            list.add(s.substring(start, start + subsize));
            start += subsize;
        }
        return list.toArray(new String[0]);
    }

    public static String[] splitBySize(String s, int size, int limit) {
        int len = s.length();
        if (len <= size)
            return new String[] { s };
        return splitBySize(s, new int[] { size }, limit);
    }

    public static String[] splitBySize(String s, int size) {
        return splitBySize(s, size, 0);
    }

    public static List<String> extractWords(String data, int maxCount) {
        if (maxCount == 0)
            return Collections.emptyList();

        List<String> list = new ArrayList<String>(Math.min(maxCount, 32));

        int pos = 0;
        int len = data.length();
        int startPos = -1;
        while (pos < len) {
            char ch = data.charAt(pos);
            if (Character.isWhitespace(ch)) {
                if (startPos != -1) {
                    list.add(data.substring(startPos, pos));
                    startPos = -1;
                    if (list.size() >= maxCount)
                        break;
                }
            } else {
                if (startPos == -1)
                    startPos = pos;
            }
            pos++;
        }
        if (startPos != -1)
            list.add(data.substring(startPos));
        return list;
    }

}
