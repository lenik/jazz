package net.bodz.bas.servlet.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import jakarta.servlet.http.Cookie;

public class Cookies {

    List<Cookie> list;

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

    public Cookies filter(Predicate<Cookie> f) {
        List<Cookie> sub = new ArrayList<>();
        for (Cookie c : this.list) {
            if (f.test(c))
                sub.add(c);
        }
        return new Cookies(sub);
    }

    public Cookies domain(String domain) {
        return filter(c -> matchDomain(domain, c.getDomain()));
    }

    static boolean matchDomain(String pattern, String domain) {
        return pattern.matches(domain);
    }

}
