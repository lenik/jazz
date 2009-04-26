package net.bodz.dist.pro.sysid;

import net.bodz.bas.lang.err.SystemException;
import net.bodz.dist.pro.sysid.MacAddressId;

public class MacAddressIdTest {

    public static void main(String[] args) throws SystemException {
        MacAddressId macId = new MacAddressId(0);
        String s = macId.getIdString();
        System.out.println("Mac: [" + s + "]"); //$NON-NLS-1$ //$NON-NLS-2$
    }

}
