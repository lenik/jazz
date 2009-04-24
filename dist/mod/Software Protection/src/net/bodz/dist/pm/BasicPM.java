package net.bodz.dist.pm;

import net.bodz.bas.lang.err.SystemException;
import net.bodz.dist.seals.IdSeededSequence;
import net.bodz.dist.seals.Sequence;
import net.bodz.dist.sysid.MacAddressId;
import net.bodz.dist.sysid.SysIdProvider;
import net.bodz.dist.sysid.VolumeId;

public class BasicPM extends PMv1 {

    SysIdProvider sysIdProvider;
    Sequence      userSeq;

    public BasicPM() throws SystemException {
        SysIdProvider[] list = {
        //
                new MacAddressId(0), //
                new VolumeId("C:/"), //  //$NON-NLS-1$
        };
        for (SysIdProvider sip : list) {
            byte[] id = sip.getId();
            if (id == null)
                continue;
            sysIdProvider = sip;
        }
        if (sysIdProvider == null)
            throw new SystemException("Can't identify this machine.");
        userSeq = new IdSeededSequence() {
            @Override
            protected SysIdProvider findIdProvider() {
                return sysIdProvider;
            }
        };
    }

    @Override
    public Sequence getUserSequence() {
        return userSeq;
    }

    @Override
    public void precrypt() {
    }

    static {
        try {
            ProtectionModels.register("A", new BasicPM());
        } catch (SystemException e) {
            throw new RuntimeException(e);
        }
    }

}
