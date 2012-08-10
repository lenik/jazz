package net.bodz.redist.obfuz.sysid;

import net.bodz.bas.err.SystemException;

public class MacAddressIdTest {

    public static void main(String[] args)
            throws SystemException {
        MacAddressId macId = new MacAddressId(0);
        String s = macId.getIdString();
        System.out.println("Mac: [" + s + "]");
    }

}
