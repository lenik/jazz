package net.bodz.redist.obfuz.sysid;

import org.junit.Test;

import net.bodz.bas.c.java.util.MapDump;
import net.bodz.bas.t.order.ComparableComparator;

public class WMISystemInfoTest {

    @Test
    public void testWmic()
            throws Exception {
        String output = WMISystemInfo.wmic("diskdrive", "list", "full");
        System.out.println(output);
    }

    @Test
    public void testInstance()
            throws Exception {
        WMISystemInfo info = WMISystemInfo.getInstance();
        String dump = MapDump.dump(info, ComparableComparator.getRawInstance());
        System.out.println(dump);
    }

}
