package net.bodz.redist.obfuz.sysid;

import org.junit.Test;

import net.bodz.bas.err.SystemException;

public class VolumeIdTest {

    @Test
    public void test()
            throws SystemException {
        VolumeId volId = new VolumeId("C:/");
        String id = volId.getIdString();
        System.out.println("Volume Id = " + id);
    }

}
