package net.bodz.bas.sec.pki.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.security.Provider;
import java.security.ProviderException;
import java.security.Security;
import java.security.Provider.Service;
import java.util.Map.Entry;

import net.bodz.bas.io.Files;
import net.bodz.bas.io.CharOuts.TempCharOut;
import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.net.CURL;
import net.bodz.bas.net.CURL.Alpha;
import net.bodz.bas.types.TextMap;
import net.bodz.bas.types.TypeParser;
import net.bodz.bas.types.TextMap.HashTextMap;
import sun.security.pkcs11.SunPKCS11;
import sun.security.pkcs11.wrapper.PKCS11;
import sun.security.pkcs11.wrapper.PKCS11Exception;

public class Providers {

    private static final Parser parser = new Parser();

    /**
     * @see PKCS11Parser
     */
    public static Provider parse(String s) throws ParseException {
        return parser.parse(s);
    }

    public static class Parser implements TypeParser {

        @Override
        public Provider parse(String s) throws ParseException {
            Provider provider = Security.getProvider(s);
            if (provider != null)
                return provider;
            int p = s.indexOf("://");
            String type;
            if (p != -1) {
                type = s.substring(0, p);
                s = s.substring(p + 3);
            } else {
                type = s;
                s = null;
            }
            TypeParser parser = parsers.get(type);
            if (parser == null)
                throw new ParseException("No such provider: " + type);
            return (Provider) parser.parse(s);
        }

    }

    static TextMap<TypeParser> parsers;
    static {
        parsers = new HashTextMap<TypeParser>();
        parsers.put("PKCS11", new PKCS11Parser());
    }

    /**
     * This parser will register PKCS11 providers to the {@link Security}
     * registry.
     * <ul>
     * <li><code>PKCS11://config-file</code>
     * <li><code>PKCS11://library#slot?name=installed-name&...</code>
     * </ul>
     * Slot format:
     * <ul>
     * <li><code>slot-list-index</code>
     * <li><code>:slot-id</code>
     * <li><code>*</code> (auto search)
     * </ul>
     */
    static class PKCS11Parser implements TypeParser {

        TempCharOut getTemp() throws IOException {
            File tempFile = File.createTempFile("p11conf", ".ini");
            return new TempCharOut(tempFile, "utf-8");
        }

        static Field _p11;

        static PKCS11 getP11(SunPKCS11 obj) {
            try {
                if (_p11 == null) {
                    _p11 = SunPKCS11.class.getDeclaredField("p11");
                    _p11.setAccessible(true);
                }
                return (PKCS11) _p11.get(obj);
            } catch (Exception e) {
                throw new Error(e);
            }
        }

        boolean hasToken(Provider provider) {
            // has any token?
            Service service = provider.getService("KeyStore", "PKCS11");
            return service != null;
        }

        @Override
        public Provider parse(String s) throws ParseException {
            CURL curl = new CURL(s);
            if (curl.getType() != null)
                throw new ParseException("the type should be stripped.");
            Alpha[] alphas = curl.getAlphas();
            String path = alphas[0].formatBetas();
            Provider provider = null;
            if (alphas.length == 1) { // config-file
                provider = new SunPKCS11(path);
            } else { // library-file
                String name = curl.getParameter("name");
                if (name != null) {
                    String fname = SunPKCS11.class.getSimpleName() + "-" + name;
                    provider = Security.getProvider(fname);
                    if (provider != null)
                        return provider;
                }
                String library = alphas[0].formatBetas();
                String slot = alphas[1].formatBetas();
                TextMap<String> parameters = curl.getParameters();

                try {
                    if ("*".equals(slot)) { // auto-search
                        SunPKCS11 sprov = tryConfig(name, library, "0",
                                parameters);
                        if (!hasToken(sprov)) {
                            PKCS11 p11 = getP11(sprov);
                            long[] slots;
                            try {
                                slots = p11.C_GetSlotList(true);
                            } catch (PKCS11Exception e) {
                                throw new ParseException(e);
                            }
                            if (slots.length == 0)
                                throw new ParseException(
                                        "no slot is token available!");
                            int got = -1;
                            for (int i = 0; i < slots.length; i++) {
                                String slotId = ":" + slots[i];
                                sprov = tryConfig(name, library, slotId,
                                        parameters);
                                if (hasToken(sprov)) {
                                    got = i;
                                    break;
                                }
                            }
                            if (got == -1)
                                throw new ParseException("no available slot!");
                            provider = sprov;
                        }
                    } else
                        provider = tryConfig(name, library, slot, parameters);
                } catch (IOException e) {
                    throw new ParseException(e);
                }
            }
            Security.addProvider(provider);
            return provider;
        }

        SunPKCS11 tryConfig(String name, String library, String slot,
                TextMap<String> parameters) throws IOException {
            TempCharOut out = getTemp();
            if (name != null)
                out.println("name = " + name);
            if (library != null)
                out.println("library = " + library);
            if (slot != null) {
                if (slot.startsWith(":")) {
                    slot = slot.substring(1);
                    long slotId = Long.parseLong(slot);
                    out.println("slot = " + slotId);
                } else if (!slot.isEmpty()) {
                    long slotListIndex = Long.parseLong(slot);
                    out.println("slotListIndex = " + slotListIndex);
                }
            }
            if (parameters != null) {
                for (Entry<String, String> e : parameters.entrySet()) {
                    String key = e.getKey();
                    if ("name".equals(key))
                        continue;
                    String value = e.getValue();
                    out.println(key + " = " + value);
                }
            }
            out.close();
            File file = out.getFile();
            try {
                SunPKCS11 provider = new SunPKCS11(file.getPath());
                return provider;
            } catch (ProviderException e) {
                String config = Files.readAll(file, "utf-8");
                System.err.println("Config error: " + e.getMessage());
                System.err.println(config);
                throw e;
            }
        }

    }

}
