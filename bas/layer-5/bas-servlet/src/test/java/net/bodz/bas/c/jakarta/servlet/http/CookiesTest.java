package net.bodz.bas.c.jakarta.servlet.http;

import org.junit.Assert;
import org.junit.Test;

public class CookiesTest
        extends Assert {

    @Test
    public void test() {
        Cookies a = new Cookies();
        a.add("name", "Lily");
        a.add("name", "Lucy");
        a.add("age", 12);
        a.add("size", 12);
        a.add("foo-id", "Foo1").setDomain("my.foo.com");
        a.add("bar-id", "Bar1").setDomain("bar.com");
        a.add("sample", true).setPath("/sample/a");
        a.add("test", true).setPath("/test/a");
        a.dump();
        a.name("name").dump();
        a.value(12).dump();
        a.value(12).any().dump();
        // a.nameValue("age", 12).dump();
        a.domain("*.foo.com").dump();
        a.path("/test/*").dump();
    }

}
