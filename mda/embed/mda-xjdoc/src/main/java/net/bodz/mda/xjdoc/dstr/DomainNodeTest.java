package net.bodz.mda.xjdoc.dstr;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DomainNodeTest
        extends Assert {

    DomainString hello;

    @Before
    public void buildHello() {
        hello = new DomainString(null, "hello", //
                new DomainString("zh", null, //
                        new DomainString("cn", "你好"), //
                        new DomainString("tw", "类好") //
                ), //
                new DomainString("en", "Hello", //
                        new DomainString("us", "Hey")), //
                new DomainString("ja", "こんにちは"), //
                new DomainString("et", "Saluton"));
    }

    @Test
    public void testResolveNull() {
        DomainString dstr = hello.resolve(null);
        assertSame(hello, dstr);
    }

    @Test
    public void testResolveSimple() {
        DomainString dstr = hello.resolve("et");
        assertEquals("Saluton", dstr.value());
    }

    @Test
    public void testResolveUndefined() {
        DomainString dstr = hello.resolve("bad");
        assertNull(dstr);
    }

    @Test
    public void testResolve2() {
        DomainString dstr = hello.resolve("zh-cn");
        assertEquals("你好", dstr.value());
    }

    @Test
    public void testDump() {
        // System.out.print(hello.dumpContent());
        assertEquals("null: hello\net: Saluton\nja: こんにちは\nen: Hello\nen-us: Hey\nzh-tw: 类好\nzh-cn: 你好\n", //
                hello.dumpContent());
    }

    @Test
    public void testOptim() {
        hello.resolve("zh-cn");
        // System.out.print(hello.dumpContent());
        assertEquals("null: hello\nzh-cn: 你好\nzh-tw: 类好\net: Saluton\nja: こんにちは\nen: Hello\nen-us: Hey\n", //
                hello.dumpContent());
    }

}
