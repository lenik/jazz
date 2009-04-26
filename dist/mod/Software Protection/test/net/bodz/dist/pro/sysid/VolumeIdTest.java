package net.bodz.dist.pro.sysid;

import net.bodz.bas.lang.err.SystemException;
import net.bodz.dist.pro.sysid.VolumeId;

import org.junit.Test;

public class VolumeIdTest {

    @Test
    public void test() throws SystemException {
        VolumeId volId = new VolumeId("C:/"); //$NON-NLS-1$
        String id = volId.getIdString();
        System.out.println("Volume Id = " + id); //$NON-NLS-1$
    }

}
