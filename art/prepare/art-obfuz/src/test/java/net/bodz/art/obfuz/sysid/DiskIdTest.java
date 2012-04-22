package net.bodz.art.obfuz.sysid;

import net.bodz.art.obfuz.sysid.DiskId;

import org.junit.Test;

public class DiskIdTest {

    @Test
    public void test() throws Exception {
        int diskCount = DiskId.getDiskCount();
        for (int i = 0; i < diskCount; i++) {
            DiskId diskId = new DiskId(i);
            String idString = diskId.getIdString();
            System.out.println("DISKID." + i + " = " + idString);
        }
    }

}
