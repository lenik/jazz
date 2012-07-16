package net.bodz.bas.i18n.dom;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DomainNodeTest
        extends Assert {

    DomainString hello;

    @Before
    public void buildHello() {
        hello = new XDomainString(null, "hello", //
                new XDomainString("zh", null, //
                        new XDomainString("cn", "你好"), //
                        new XDomainString("tw", "类好") //
                ), //
                new XDomainString("en", "Hello", //
                        new XDomainString("us", "Hey")), //
                new XDomainString("ja", "こんにちは"), //
                new XDomainString("et", "Saluton"));
    }

    @Test
    public void testGetNull() {
        String dstr = hello.get(null);
        assertSame("hello", dstr);
    }

    @Test
    public void testGetSimple() {
        String val = hello.get("et");
        assertEquals("Saluton", val);
    }

    @Test
    public void testGetUndefined() {
        String val = hello.get("bad");
        assertNull(val);
    }

    @Test
    public void testResolve2() {
        String val = hello.pull("zh-cn");
        assertEquals("你好", val);
    }

    @Test
    public void testDump() {
        // System.out.print(hello.dumpContent());
        assertEquals("null: hello\net: Saluton\nja: こんにちは\nen: Hello\nen-us: Hey\nzh-tw: 类好\nzh-cn: 你好\n", //
                hello.dumpContent());
    }

    @Test
    public void testPullToFront() {
        hello.pull("zh-cn");
        // System.out.print(hello.dumpContent());
        assertEquals("null: hello\nzh-cn: 你好\nzh-tw: 类好\net: Saluton\nja: こんにちは\nen: Hello\nen-us: Hey\n", //
                hello.dumpContent());
    }

}
