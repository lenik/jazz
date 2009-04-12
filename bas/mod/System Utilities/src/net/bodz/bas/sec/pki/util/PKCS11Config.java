package net.bodz.bas.sec.pki.util;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.io.CharOut;
import net.bodz.bas.io.Files;
import net.bodz.bas.lang.err.UnexpectedException;
import net.bodz.bas.types.TextMap;
import net.bodz.bas.types.TextMap.TreeTextMap;
import sun.security.pkcs11.wrapper.PKCS11Constants;

public class PKCS11Config {

    private String           name;
    private File             library;
    private String           description;
    private Integer          slot;
    private Integer          slotListIndex;
    private Collection<Long> enabledMechanisms;
    private Collection<Long> disabledMechanisms;
    private TextMap<String>  attributes;

    public PKCS11Config(String name, File library) {
        this.name = name;
        this.library = library;
    }

    public PKCS11Config(String name, File library, int slotListIndex) {
        this(name, library);
        this.slotListIndex = slotListIndex;
    }

    /**
     * name suffix of this provider instance
     * <p>
     * This string is concatenated with the prefix SunPKCS11- to produce this
     * provider instance's name (that is, the the string returned by its
     * Provider.getName() method). For example, if the name attribute is
     * "FooAccelerator", then the provider instance's name will be
     * "SunPKCS11-FooAccelerator".
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * pathname of PKCS#11 implementation
     * <p>
     * This is the full pathname (including extension) of the PKCS#11
     * implementation; the format of the pathname is platform dependent. For
     * example, /opt/foo/lib/libpkcs11.so might be the pathname of a PKCS#11
     * implementation on Solaris and Linux while C:\foo\mypkcs11.dll might be
     * one on Windows.
     */
    public File getLibrary() {
        return library;
    }

    public void setLibrary(String library) {
        this.library = Files.canoniOf(library);
    }

    public void setLibrary(File library) {
        this.library = Files.canoniOf(library);
    }

    /**
     * description of this provider instance
     * <p>
     * This string will be returned by the provider instance's
     * Provider.getInfo() method. If none is specified, a default description
     * will be returned.
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * slot id
     * <p>
     * This is the id of the slot that this provider instance is to be
     * associated with. For example, you would use 1 for the slot with the id 1
     * under PKCS#11. At most one of slot or slotListIndex may be specified. If
     * neither is specified, the default is a slotListIndex of 0.
     */
    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
        this.slotListIndex = null;
    }

    /**
     * slot index
     * <p>
     * This is the slot index that this provider instance is to be associated
     * with. It is the index into the list of all slots returned by the PKCS#11
     * function C_GetSlotList. For example, 0 indicates the first slot in the
     * list. At most one of slot or slotListIndex may be specified. If neither
     * is specified, the default is a slotListIndex of 0.
     */
    public int getSlotListIndex() {
        return slotListIndex;
    }

    public void setSlotListIndex(int slotListIndex) {
        this.slot = null;
        this.slotListIndex = slotListIndex;
    }

    /**
     * @return <code>true</code> using slot-id, or <code>false</code> using
     *         slot-list-index.
     */
    public boolean isSlotId() {
        return slot != null;
    }

    /**
     * brace enclosed, whitespace-separated list of PKCS#11 mechanisms to enable
     * <p>
     * This is the list PKCS#11 mechanisms that this provider instance should
     * use, provided that they are supported by both the Sun PKCS#11 provider
     * and PKCS#11 token. All other mechanisms will be ignored. Each entry in
     * the list is the name of a PKCS#11 mechanism. Here is an example that
     * lists two PKCS#11 mechanisms.
     * 
     * <pre>
     * enabledMechanisms = {
     *   CKM_RSA_PKCS
     *   CKM_RSA_PKCS_KEY_PAIR_GEN
     * }
     * </pre>
     * 
     * At most one of enabledMechanisms or disabledMechanisms may be specified.
     * If neither is specified, the mechanisms enabled are those that are
     * supported by both the Sun PKCS#11 provider and the PKCS#11 token.
     * 
     * @see PKCS11Constants
     * @see PKCS11Constants#CKM_RSA_PKCS
     * @see PKCS11Constants#CKM_RSA_PKCS_KEY_PAIR_GEN
     */
    public Collection<Long> getEnabledMechanisms() {
        return enabledMechanisms;
    }

    public void addEnabledMechanism(long mechanism) {
        if (enabledMechanisms == null)
            enabledMechanisms = new ArrayList<Long>();
        if (enabledMechanisms.contains(mechanism))
            enabledMechanisms.add(mechanism);
    }

    public boolean removeEnabledMechanism(long mechanism) {
        if (enabledMechanisms == null)
            return false;
        return enabledMechanisms.remove(mechanism);
    }

    /**
     * brace enclosed, whitespace-separated list of PKCS#11 mechanisms to
     * disable
     * <p>
     * This is the list of PKCS#11 mechanism that this provider instance should
     * ignore. Any mechanism listed will be ignored by the provider, even if
     * they are supported by the token and the Sun PKCS#11 provider. The strings
     * SecureRandom and KeyStore may be specified to disable those services.
     * <p>
     * At most one of enabledMechanisms or disabledMechanisms may be specified.
     * If neither is specified, the mechanisms enabled are those that are
     * supported by both the Sun PKCS#11 provider and the PKCS#11 token
     * 
     * @see PKCS11Constants
     * @see PKCS11Constants#CKM_RSA_PKCS
     * @see PKCS11Constants#CKM_RSA_PKCS_KEY_PAIR_GEN
     */
    public Collection<Long> getDisabledMechanisms() {
        return disabledMechanisms;
    }

    public void addDisabledMechanism(long mechanism) {
        if (disabledMechanisms == null)
            disabledMechanisms = new ArrayList<Long>();
        if (disabledMechanisms.contains(mechanism))
            disabledMechanisms.add(mechanism);

    }

    public boolean removeDisabledMechanism(long mechanism) {
        if (disabledMechanisms == null)
            return false;
        return disabledMechanisms.remove(mechanism);
    }

    /**
     * The attributes option can be used to specify additional PKCS#11 that
     * should be set when creating PKCS#11 key objects. This makes it possible
     * to accomodate tokens that require particular attributes. For details, see
     * the section below.
     */
    public TextMap<String> getAttributes() {
        return attributes;
    }

    public void setAttribute(String name, String value) {
        if (attributes == null)
            attributes = new TreeTextMap<String>();
        attributes.put(name, value);
    }

    public String removeAttribute(String name) {
        if (attributes == null)
            return null;
        return (String) attributes.remove(name);
    }

    public void dump(CharOut out) {
        if (name != null)
            out.println("name = " + name); //$NON-NLS-1$
        if (library != null)
            out.println("library = " + library.getPath()); //$NON-NLS-1$
        if (slot != null)
            out.println("slot = " + slot); //$NON-NLS-1$
        else if (slotListIndex != null)
            out.println("slotListIndex = " + slotListIndex); //$NON-NLS-1$
        if (enabledMechanisms != null)
            out.println("enabledMechanisms = " //$NON-NLS-1$
                    + dumpMechanisms(enabledMechanisms));
        if (disabledMechanisms != null)
            out.println("disabledMechanisms = " //$NON-NLS-1$
                    + dumpMechanisms(disabledMechanisms));
        if (attributes != null)
            for (Entry<String, String> e : attributes.entrySet()) {
                String name = e.getKey();
                String value = e.getValue();
                out.println(name + " = " + value); //$NON-NLS-1$
            }
    }

    static Map<Long, String> mechanismNames;
    static {
        mechanismNames = new HashMap<Long, String>();
        final int PSF = Modifier.PUBLIC | Modifier.STATIC | Modifier.FINAL;
        try {
            for (Field field : PKCS11Constants.class.getDeclaredFields()) {
                int modifiers = field.getModifiers();
                if ((modifiers & PSF) != PSF)
                    continue;
                String name = field.getName();
                if (!name.startsWith("CKM_")) //$NON-NLS-1$
                    continue;
                Object value = field.get(null);
                assert value instanceof Long;
                mechanismNames.put((Long) value, name);
            }
        } catch (Exception e) {
            throw new UnexpectedException(e);
        }
    }

    private String dumpMechanisms(Collection<Long> mechanisms) {
        assert mechanisms != null;
        StringBuffer buf = new StringBuffer(mechanisms.size() * 30);
        buf.append("{"); //$NON-NLS-1$
        for (Long mechanism : mechanisms) {
            assert mechanism != null;
            String mechanismName = mechanismNames.get(mechanism);
            buf.append("\n  "); //$NON-NLS-1$
            buf.append(mechanismName);
        }
        buf.append("\n}"); //$NON-NLS-1$
        return buf.toString();
    }

}
