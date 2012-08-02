package net.bodz.art.obfuz.pm;

import static net.bodz.art.obfuz.nls.ProtectNLS.ProtectNLS;

import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import net.bodz.art.obfuz.seals.IdSeededSequence;
import net.bodz.art.obfuz.seals.Sequence;
import net.bodz.art.obfuz.sysid.SysIdProvider;
import net.bodz.bas.c.java.util.HashTextMap;
import net.bodz.bas.c.java.util.TextMap;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.SystemException;
import net.bodz.bas.text.codec.builtin.HexCodec;

public abstract class AbstractProtectionModel
        implements ProtectionModel {

    private final SysIdProvider sysIdProvider;
    private final int seed;
    private final TextMap<VirtualMachine> machines;
    private final TextMap<Section> sections;

    public AbstractProtectionModel(SysIdProvider sysIdProvider, int seed) {
        if (sysIdProvider == null)
            throw new NullPointerException("sysIdProvider");
        this.sysIdProvider = sysIdProvider;
        this.seed = seed;
        this.machines = new HashTextMap<VirtualMachine>();
        this.sections = new HashTextMap<Section>();
    }

    @Override
    public VirtualMachine getVM(String type) {
        VirtualMachine vm = machines.get(type);
        if (vm == null)
            throw new IllegalUsageException(ProtectNLS.getString("_ProtectionModel.vmIsntExisted") + type);
        return vm;
    }

    @Override
    public SysIdProvider getSysIdProvider() {
        return sysIdProvider;
    }

    @Override
    public Sequence getSequence()
            throws ProtectException {
        try {
            return new IdSeededSequence(seed) {
                @Override
                protected SysIdProvider findIdProvider() {
                    return getSysIdProvider();
                }
            };
        } catch (SystemException e) {
            throw new ProtectException(e);
        }
    }

    protected abstract String[] getSectionNames();

    @Override
    public Section[] getSections() {
        Section[] sections;
        String[] names = getSectionNames();
        if (names == null)
            throw new NullPointerException("section-list");
        sections = new Section[names.length];
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            Section section = getSection(name);
            if (section == null)
                throw new NullPointerException(ProtectNLS.getString("_ProtectionModel.sectionIsntDefined") + name);
            sections[i] = section;
        }
        return sections;
    }

    @Override
    public Section getSection(String name) {
        return sections.get(name);
    }

    private static final byte[] salt = "*master-model*".getBytes();

    @Override
    public SecretKey keygen(String passphrase)
            throws ProtectException {
        // Create the key
        KeySpec keySpec = new PBEKeySpec(passphrase.toCharArray(), salt, 3);
        try {
            SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
            return key;
        } catch (GeneralSecurityException e) {
            throw new ProtectException(e);
        }
    }

    @Override
    public SecretKey keygen(byte[] keybytes)
            throws ProtectException {
        String hex = HexCodec.getInstance().encode(keybytes);
        return keygen(hex);
    }

    private static SecureRandom random;

    @Override
    public KeyPair keygen2()
            throws ProtectException {
        try {
            if (random == null)
                random = SecureRandom.getInstance("SHA1PRNG");
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("DSA");
        } catch (Exception e) {
            throw new ProtectException(e);
        }
        return null;
    }

    private Cipher ecipher;
    private Cipher dcipher;

    @Override
    public byte[] encrypt(Key key, byte[] text)
            throws ProtectException {
        try {
            if (ecipher == null) {
                ecipher = Cipher.getInstance("DES");
                ecipher.init(Cipher.ENCRYPT_MODE, key);
            }
            byte[] result = ecipher.doFinal(text);
            return result;
        } catch (GeneralSecurityException e) {
            throw new ProtectException(e);
        }
    }

    @Override
    public byte[] decrypt(Key KEY, byte[] secret)
            throws ProtectException {
        try {
            if (dcipher == null) {
                dcipher = Cipher.getInstance("DES");
                dcipher.init(Cipher.DECRYPT_MODE, KEY);
            }
            byte[] result = dcipher.doFinal(secret);
            return result;
        } catch (GeneralSecurityException e) {
            throw new ProtectException(e);
        }
    }

}
