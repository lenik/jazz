package net.bodz.dist.pm;

import java.security.GeneralSecurityException;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import net.bodz.bas.lang.err.IllegalUsageException;
import net.bodz.bas.types.HashTextMap;
import net.bodz.bas.types.TextMap;
import net.bodz.dist.sysid.SysIdProvider;

public abstract class _ProtectionModel implements ProtectionModel {

    private final SysIdProvider           sysIdProvider;
    private final TextMap<VirtualMachine> machines;
    private final TextMap<Section>        sections;

    public _ProtectionModel(SysIdProvider sysIdProvider) {
        if (sysIdProvider == null)
            throw new NullPointerException("sysIdProvider");
        this.sysIdProvider = sysIdProvider;
        this.machines = new HashTextMap<VirtualMachine>();
        this.sections = new HashTextMap<Section>();
    }

    @Override
    public VirtualMachine getVM(String type) {
        VirtualMachine vm = machines.get(type);
        if (vm == null)
            throw new IllegalUsageException("VM for specified type isn't existed: " + type);
        return vm;
    }

    @Override
    public SysIdProvider getSysIdProvider() {
        return sysIdProvider;
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
                throw new NullPointerException("Section isn't defined: " + name);
            sections[i] = section;
        }
        return sections;
    }

    @Override
    public Section getSection(String name) {
        return sections.get(name);
    }

    private Cipher ecipher;
    private Cipher dcipher;

    byte[]         salt           = {
                                  //
            (byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32, //
            (byte) 0x56, (byte) 0x35, (byte) 0xE3, (byte) 0x03, };
    int            iterationCount = 19;

    SecretKey generateSK(String passPhrase) throws ProtectException {
        // Create the key
        KeySpec keySpec = new PBEKeySpec(passPhrase.toCharArray(), salt, iterationCount);
        try {
            SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES")
                    .generateSecret(keySpec);
        } catch (GeneralSecurityException e) {
            throw new ProtectException(e);
        }
    }

    @Override
    public byte[] encrypt(Key key, byte[] text) throws ProtectException {
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
    public byte[] decrypt(Key KEY, byte[] secret) throws ProtectException {
        try {
            if (dcipher == null) {
                dcipher = Cipher.getInstance("DES");
                dcipher.init(Cipher.DECRYPT_MODE, key);
            }
            byte[] result = dcipher.doFinal(secret);
            return result;
        } catch (GeneralSecurityException e) {
            throw new ProtectException(e);
        }
    }

}
