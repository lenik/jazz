package net.bodz.dist.pm;

import net.bodz.dist.sysid.SysIdProvider;

public interface ProtectionModel {

    /**
     * @param name
     * @return never <code>null</code>
     */
    VirtualMachine getVM(String type);

    /**
     * @return never <code>null</code>
     */
    SysIdProvider getSysIdProvider();

    /**
     * General purpose encryption method
     */
    byte[] encrypt(Key key, byte[] text) throws ProtectException;

    /**
     * General purpose decryption method
     */
    byte[] decrypt(Key KEY, byte[] secret) throws ProtectException;

    /**
     * @return maybe <code>null</code>
     */
    Section getSection(String name);

    /**
     * @return section names in order, never <code>null</code>
     */
    Section[] getSections();

}
