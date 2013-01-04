package net.bodz.bas.i18n.dom;

import java.util.Map.Entry;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class XDomainNodeTest
        extends Assert {

    iString hello;

    @Before
    public void buildHello() {
        hello = new XiString(null, "hello", //
                new XiString("zh", null, //
                        new XiString("cn", "你好"), //
                        new XiString("tw", "类好") //
                ), //
                new XiString("en", "Hello", //
                        new XiString("us", "Hey")), //
                new XiString("ja", "こんにちは"), //
                new XiString("et", "Saluton"));
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

    @Test
    public void testEntrySet() {
        for (Entry<String, String> entry : hello.entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }
    }

}
