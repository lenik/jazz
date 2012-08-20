package net.bodz.bas.c.javax.security.auth;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.security.Provider;
import java.security.Provider.Service;
import java.security.ProviderException;
import java.security.Security;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.c.java.net.CURL;
import net.bodz.bas.c.java.net.CURL.Alpha;
import net.bodz.bas.c.java.util.HashTextMap;
import net.bodz.bas.c.java.util.TextMap;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.io.tmp.TempCharOut;
import net.bodz.bas.sio.ICharOut;
import net.bodz.bas.sio.IPrintOut;
import net.bodz.bas.sio.PrintOutImpl;
import net.bodz.bas.traits.IParser;

public class Providers {

    private static final Parser parser = new Parser();

    /**
     * @see PKCS11Parser
     */
    public static Provider parse(String s)
            throws ParseException {
        return parser.parse(s);
    }

    public static class Parser
            implements IParser<Provider> {

        @Override
        public Provider parse(String s)
                throws ParseException {
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
            IParser parser = parsers.get(type);
            if (parser == null)
                throw new ParseException(SysNLS.getString("Providers.noProvider") + type);
            return (Provider) parser.parse(s);
        }

    }

    static TextMap<IParser<Provider>> parsers;
    static {
        parsers = new HashTextMap<IParser<Provider>>();
        parsers.put("PKCS11", new PKCS11Parser());
    }

    /**
     * This parser will register PKCS11 providers to the {@link Security} registry.
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
    static class PKCS11Parser
            implements IParser<Provider> {

        IPrintOut getTemp()
                throws IOException {
            File tempFile = File.createTempFile("p11conf", ".ini");
            Charset utf8 = Charset.forName("utf-8");
            ICharOut tempOut = new TempCharOut(tempFile, utf8);
            return PrintOutImpl.from(tempOut);
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
        public Provider parse(String s)
                throws ParseException {
            CURL curl = new CURL(s);
            if (curl.getType() != null)
                throw new ParseException(SysNLS.getString("Providers.typeShouldBeStripped"));
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
                Map<String, String> parameters = curl.getParameters();

                try {
                    if ("*".equals(slot)) { // auto-search
                        SunPKCS11 sprov = tryConfig(name, library, "0", parameters);
                        if (!hasToken(sprov)) {
                            PKCS11 p11 = getP11(sprov);
                            long[] slots;
                            try {
                                slots = p11.C_GetSlotList(true);
                            } catch (PKCS11Exception e) {
                                throw new ParseException(e);
                            }
                            if (slots.length == 0)
                                throw new ParseException(SysNLS.getString("Providers.noSlot"));
                            int got = -1;
                            for (int i = 0; i < slots.length; i++) {
                                String slotId = ":" + slots[i];
                                sprov = tryConfig(name, library, slotId, parameters);
                                if (hasToken(sprov)) {
                                    got = i;
                                    break;
                                }
                            }
                            if (got == -1)
                                throw new ParseException(SysNLS.getString("Providers.noAvailSlot"));
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

        SunPKCS11 tryConfig(String name, String library, String slot, Map<String, String> parameters)
                throws IOException {
            IPrintOut out = getTemp();
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
                System.err.println(SysNLS.getString("Providers.configError") + e.getMessage());
                System.err.println(config);
                throw e;
            }
        }

    }

}
