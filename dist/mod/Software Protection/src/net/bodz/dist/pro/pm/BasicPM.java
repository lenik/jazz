package net.bodz.dist.pro.pm;

import java.security.Key;

import javax.crypto.SecretKey;

import net.bodz.bas.lang.err.SystemException;
import net.bodz.dist.pro.sysid.DiskId;
import net.bodz.dist.pro.sysid.MacAddressId;
import net.bodz.dist.pro.sysid.SysIdProvider;
import net.bodz.dist.pro.sysid.VolumeId;

public class BasicPM extends _ProtectionModel {

    public BasicPM(int seed) throws SystemException {
        super(findAvailableSysIdProvider(), seed);
    }

    static SysIdProvider findAvailableSysIdProvider() throws SystemException {
        SysIdProvider[] list = {
        //
                new DiskId(0), //
                new MacAddressId(0), //
                new VolumeId("C:/"), //  //$NON-NLS-1$
        };
        for (SysIdProvider sip : list) {
            byte[] id = sip.getId();
            if (id != null)
                return sip;
        }
        throw new SystemException("Can't identify this machine.");
    }

    @Override
    protected String[] getSectionNames() {
        return null;
    }

    @Override
    public SecretKey keygen(byte[] keybytes) throws ProtectException {
        return null;
    }

    @Override
    public byte[] protectKey(Key privateKey, Key skey) throws ProtectException {
        return null;
    }

    @Override
    public Key restoreKey(Key publicKey, byte[] skeyEncoded) throws ProtectException {
        return null;
    }

}
