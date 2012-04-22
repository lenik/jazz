package net.bodz.art.obfuz.sysid;

import net.bodz.bas.util.order.NaturalComparator;

import org.junit.Test;

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
        String dump = Maps.dump(info, new NaturalComparator<>());
        System.out.println(dump);
    }

}
