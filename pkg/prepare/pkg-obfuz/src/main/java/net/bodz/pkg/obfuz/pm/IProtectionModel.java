package net.bodz.pkg.obfuz.pm;

import java.security.Key;
import java.security.KeyPair;

import javax.crypto.SecretKey;

import net.bodz.pkg.obfuz.seals.ISequence;
import net.bodz.pkg.obfuz.sysid.ISysIdProvider;

public interface IProtectionModel {

    /**
     * @param name
     * @return never <code>null</code>
     */
    VirtualMachine getVM(String type);

    /**
     * @return never <code>null</code>
     */
    ISysIdProvider getSysIdProvider();

    ISequence getSequence()
            throws ProtectException;

    SecretKey keygen(String passphrase)
            throws ProtectException;

    SecretKey keygen(byte[] keybytes)
            throws ProtectException;

    KeyPair keygen2()
            throws ProtectException;

    /**
     * General purpose encryption method
     */
    byte[] encrypt(Key skey, byte[] text)
            throws ProtectException;

    /**
     * General purpose decryption method
     */
    byte[] decrypt(Key skey, byte[] secret)
            throws ProtectException;

    byte[] protectKey(Key privateKey, Key skey)
            throws ProtectException;

    Key restoreKey(Key publicKey, byte[] skeyEncoded)
            throws ProtectException;

    /**
     * @return maybe <code>null</code>
     */
    ISection getSection(String name);

    /**
     * @return section names in order, never <code>null</code>
     */
    ISection[] getSections();

}
