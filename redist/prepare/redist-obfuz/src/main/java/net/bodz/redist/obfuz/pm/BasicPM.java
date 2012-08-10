package net.bodz.redist.obfuz.pm;

import static net.bodz.redist.obfuz.nls.ProtectNLS.ProtectNLS;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.SecretKey;

import net.bodz.redist.obfuz.sysid.CpuId;
import net.bodz.redist.obfuz.sysid.MacAddressId;
import net.bodz.redist.obfuz.sysid.SysIdProvider;
import net.bodz.bas.err.SystemException;

public class BasicPM
        extends AbstractProtectionModel {

    public BasicPM(int seed)
            throws SystemException {
        super(findAvailableSysIdProvider(), seed);
    }

    static SysIdProvider findAvailableSysIdProvider()
            throws SystemException {
        List<SysIdProvider> probeList = new ArrayList<SysIdProvider>();
        probeList.add(new CpuId(0));
        probeList.add(new MacAddressId(0));
        // list.add(new DiskId(0));
        // list.add(new VolumeId("C:/"));

        for (SysIdProvider sip : probeList) {
            byte[] id = sip.getId();
            if (id != null)
                return sip;
        }
        throw new SystemException(ProtectNLS.getString("BasicPM.cantIdentify"));
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
