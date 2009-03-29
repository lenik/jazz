package net.bodz.swt.gui.pfl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SymlinkPageFlowTest {

    @Test
    public void test1() {
        SymlinkPageFlow flow = new SymlinkPageFlow();

        flow.putLink("aaa", "xxx");
        flow.putLink("aaa/next", "bbb"); // [xxx]/next -> [yyy]/next -> null
        flow.putLink("bbb/next", "ccc");
        flow.putLink("xxx", "yyy");
        flow.putLink("xxx/next", "zzz");

        assertEquals("yyy", flow.resolv(null, "xxx"));
        assertEquals("yyy", flow.resolv(null, "aaa"));
        assertEquals("bbb", flow.resolv("aaa", "next"));
        assertEquals("ccc", flow.resolv("bbb", null));
        assertEquals("ccc/next", flow.resolv("ccc", null));
        assertEquals("zzz", flow.resolv("xxx", null));
    }

}
