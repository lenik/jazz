package net.bodz.pkg.obfuz.sysid;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.c.java.util.MapDump;
import net.bodz.bas.t.order.DefaultComparator;

public class WMISystemInfoTest
        extends Assert {

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
        String dump = MapDump.dump(info, DefaultComparator.INSTANCE);
        System.out.println(dump);
    }

}
