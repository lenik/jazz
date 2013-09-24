package net.bodz.pkg.obfuz.pm;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.SecretKey;

import net.bodz.bas.err.SystemException;
import net.bodz.pkg.obfuz.sysid.ISysIdProvider;
import net.bodz.pkg.obfuz.sysid.MacAddressId;

public class BasicPM
        extends AbstractProtectionModel {

    public BasicPM(int seed)
            throws SystemException {
        super(findAvailableSysIdProvider(), seed);
    }

    static ISysIdProvider findAvailableSysIdProvider()
            throws SystemException {
        List<ISysIdProvider> providers = new ArrayList<ISysIdProvider>();
        // providers.add(new CpuId(0));
        providers.add(new MacAddressId(0));
        // list.add(new DiskId(0));
        // list.add(new VolumeId("C:/"));

        for (ISysIdProvider provider : providers) {
            byte[] id = provider.getId();
            if (id != null)
                return provider;
        }
        throw new SystemException(tr._("Can\'t identify this machine."));
    }

    @Override
    protected String[] getSectionNames() {
        return null;
    }

    @Override
    public SecretKey keygen(byte[] keybytes)
            throws ProtectException {
        return null;
    }

    @Override
    public byte[] protectKey(Key privateKey, Key skey)
            throws ProtectException {
        return null;
    }

    @Override
    public Key restoreKey(Key publicKey, byte[] skeyEncoded)
            throws ProtectException {
        return null;
    }

}
