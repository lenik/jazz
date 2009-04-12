package net.bodz.swt.gui.pfl;

import static org.junit.Assert.assertEquals;
import net.bodz.bas.lang.err.ParseException;

import org.junit.Test;

public class SymlinkPageFlowTest {

    @Test
    public void test1() throws ParseException {
        SymlinkPageFlow flow = new SymlinkPageFlow();

        flow.putLink("aaa", "xxx"); //$NON-NLS-1$ //$NON-NLS-2$
        flow.putLink("aaa/next", "bbb"); // [xxx]/next -> [yyy]/next -> null //$NON-NLS-1$ //$NON-NLS-2$
        flow.putLink("bbb/next", "ccc"); //$NON-NLS-1$ //$NON-NLS-2$
        flow.putLink("xxx", "yyy"); //$NON-NLS-1$ //$NON-NLS-2$
        flow.putLink("xxx/next", "zzz"); //$NON-NLS-1$ //$NON-NLS-2$

        assertEquals("yyy", flow.resolv(null, "xxx")); //$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("yyy", flow.resolv(null, "aaa")); //$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("bbb", flow.resolv("aaa", "next")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        assertEquals("ccc", flow.resolv("bbb", null)); //$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("ccc/next", flow.resolv("ccc", null)); //$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("zzz", flow.resolv("xxx", null)); //$NON-NLS-1$ //$NON-NLS-2$

        String xml = flow.dumpXML();
        System.out.println(xml);

        SymlinkPageFlow reload = new SymlinkPageFlow();
        reload.loadXML(xml);
        String xml2 = reload.dumpXML();
        assertEquals(xml, xml2);
    }

}
