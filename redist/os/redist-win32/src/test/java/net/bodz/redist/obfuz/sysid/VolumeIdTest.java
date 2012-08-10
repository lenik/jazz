package net.bodz.redist.obfuz.sysid;

import net.bodz.bas.err.SystemException;

import org.junit.Test;

public class VolumeIdTest {

    @Test
    public void test()
            throws SystemException {
        VolumeId volId = new VolumeId("C:/");
        String id = volId.getIdString();
        System.out.println("Volume Id = " + id);
    }

}
