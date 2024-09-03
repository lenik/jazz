package net.bodz.bas.c.jakarta.servlet.http;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

import jakarta.servlet.http.Cookie;

import net.bodz.bas.c.object.Nullables;

public class Cookies
        implements
            Iterable<Cookie>,
            Serializable {

    private static final long serialVersionUID = 1L;

    static final boolean defaultMatchNull = false;

    List<Cookie> list;

    public Cookies() {
        this.list = new ArrayList<>();
    }

    public Cookies(Cookie... array) {
        if (array == null)
            throw new NullPointerException("array");
        this.list = Arrays.asList(array);
    }

    public Cookies(List<Cookie> list) {
        if (list == null)
            throw new NullPointerException("list");
        this.list = list;
    }

    public Cookie add(String name, Object value) {
        return add(name, Nullables.toString(value));
    }

    public Cookie add(String name, String value) {
        Cookie cookie = new Cookie(name, value);
        add(cookie);
        return cookie;
    }

    public boolean add(Cookie cookie) {
        return list.add(cookie);
    }

    public void add(int index, Cookie cookie) {
        list.add(index, cookie);
    }

    @Override
    public Iterator<Cookie> iterator() {
        return list.iterator();
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public boolean remove(Cookie o) {
        return list.remove(o);
    }

    public void clear() {
        list.clear();
    }

    public Cookie get(int index) {
        return list.get(index);
    }

    public Cookie remove(int index) {
        return list.remove(index);
    }

    public Cookies any() {
        if (list.isEmpty())
            return new Cookies();
        int n = size();
        int index = new Random().nextInt(n);
        Cookie item = list.get(index);
        return new Cookies(item);
    }

    public Cookie first() {
        if (list.isEmpty())
            return null;
        else
            return list.get(0);
    }

    public boolean contains(Cookie o) {
        return list.contains(o);
    }

    public Cookies filter(Predicate<Cookie> f) {
        List<Cookie> sub = new ArrayList<>();
        for (Cookie c : this.list) {
            if (f.test(c))
                sub.add(c);
        }
        return new Cookies(sub);
    }

    public Cookies domainEquals(String pattern) {
        return domainEquals(pattern, defaultMatchNull);
    }

    public Cookies domainEquals(String pattern, boolean matchNull) {
        return filter(c -> optionalEquals(c.getDomain(), pattern, matchNull));
    }

    public Cookies domain(String pattern) {
        return domain(pattern, defaultMatchNull);
    }

    public Cookies domain(String pattern, boolean matchNull) {
        return filter(c -> domainMatches(c.getDomain(), pattern, matchNull));
    }

    public Cookies pathEquals(String pattern) {
        return pathEquals(pattern, defaultMatchNull);
    }

    public Cookies pathEquals(String pattern, boolean matchNull) {
        return filter(c -> optionalEquals(c.getPath(), pattern, matchNull));
    }

    public Cookies path(String pattern) {
        return path(pattern, defaultMatchNull);
    }

    public Cookies path(String pattern, boolean matchNull) {
        return filter(c -> pathMatches(c.getPath(), pattern, matchNull));
    }

    public Cookies nameValue(String name, Object value) {
        return nameValue(name, Nullables.toString(value));
    }

    public Cookies nameValue(String name, String value) {
        return filter(c -> name.equals(c.getName())//
                && Nullables.equals(value, c.getValue()));
    }

    public Cookies name(String pattern) {
        return name(pattern, defaultMatchNull);
    }

    public Cookies name(String pattern, boolean matchNull) {
        return filter(c -> optionalEquals(c.getName(), pattern, matchNull));
    }

    public Cookies value(Object value) {
        return value(Nullables.toString(value));
    }

    public Cookies value(String pattern) {
        return value(pattern, defaultMatchNull);
    }

    public Cookies value(String pattern, boolean matchNull) {
        return filter(c -> optionalEquals(c.getValue(), pattern, matchNull));
    }

    public Cookies comment(String pattern) {
        return comment(pattern, defaultMatchNull);
    }

    public Cookies comment(String pattern, boolean matchNull) {
        return filter(c -> optionalEquals(c.getComment(), pattern, matchNull));
    }

    static boolean optionalEquals(String name, String pattern, boolean matchNull) {
        if (name == null)
            return matchNull;
        if (pattern.equals(name))
            return true;
        return false;
    }

    static boolean domainMatches(String domain, String pattern, boolean matchNull) {
        if (domain == null)
            return matchNull;
        if (pattern.equals(domain))
            return true;
        if (pattern.startsWith("*.")) {
            String base = pattern.substring(2);
            if (domain.endsWith(base)) {
                int nDomain = domain.length();
                int nBase = base.length();
                char before = domain.charAt(nDomain - nBase - 1);
                if (before == '.')
                    return true;
            }
        }
        return false;
    }

    static boolean pathMatches(String path, String pattern, boolean matchNull) {
        if (path == null)
            return matchNull;
        if (pattern.equals(path))
            return true;
        if (pattern.endsWith("/*")) {
            String prefix = pattern.substring(0, pattern.length() - 2);
            if (path.startsWith(prefix)) {
                int nPrefix = prefix.length();
                char lookAhead = path.charAt(nPrefix);
                if (lookAhead == '/')
                    return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Cookie c : list) {
            String domain = c.getDomain();
            String path = c.getPath();
            String comment = c.getComment();

            sb.append(c.getName() + "=" + c.getValue());

            if (domain != null)
                sb.append(" domain=" + c.getDomain());

            if (path != null)
                sb.append(" path=" + path);

            if (comment != null)
                sb.append(" // " + comment);
            sb.append("\n");
        }
        return sb.toString();
    }

    public void dump() {
        System.out.println(this);
    }

}
