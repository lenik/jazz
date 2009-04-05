package net.bodz.dist.pm;

import javax.crypto.Cipher;

import net.bodz.bas.lang.err.SystemException;
import net.bodz.bas.lang.err.UnexpectedException;
import net.bodz.dist.seals.IdSeededSequence;
import net.bodz.dist.seals.Sequence;
import net.bodz.dist.sysid.ConstId;
import net.bodz.dist.sysid.SysIdProvider;

public abstract class PMv1 implements ProtectionModel {

    private ReflectionVM rvm;

    // private

    public PMv1() {
        rvm = new ReflectionVM();
    }

    @Override
    public CiscVM getActivationVM() {
        return rvm;
    }

    @Override
    public Cipher getCKCipher() {
        return null;
    }

    static final ConstId defaultMasterId;
    static {
        byte[] id = { (byte) 0xDE, (byte) 0xAD, (byte) 0xCA, (byte) 0xFE };
        defaultMasterId = new ConstId(id);
    }

    @Override
    public Sequence getMasterSeq() {
        try {
            return new IdSeededSequence() {
                @Override
                protected SysIdProvider findIdProvider() {
                    return defaultMasterId;
                }
            };
        } catch (SystemException e) {
            throw new UnexpectedException(e);
        }
    }

    @Override
    public byte[] keyAdd(byte[] k1, byte[] k2) {

        return null;
    }

    @Override
    public byte[] keyInv(byte[] k) {
        return null;
    }

    @Override
    public void precrypt() {
    }

}
