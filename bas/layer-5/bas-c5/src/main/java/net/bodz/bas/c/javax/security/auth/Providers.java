package net.bodz.bas.c.javax.security.auth;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.Provider;
import java.security.Provider.Service;
import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import net.bodz.bas.c.java.net.CURL;
import net.bodz.bas.c.java.net.CURL.Alpha;
import net.bodz.bas.c.java.nio.Charsets;
import net.bodz.bas.c.java.util.HashTextMap;
import net.bodz.bas.c.java.util.TextMap;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.i18n.nls.II18nCapable;
import net.bodz.bas.io.tmp.TempOut;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.typer.std.AbstractParser;
import net.bodz.bas.typer.std.IParser;

@SuppressWarnings("all")
public class Providers
        implements
            II18nCapable {

    private static final Parser parser = new Parser();

    /**
     * @see PKCS11Parser
     */
    public static Provider parse(String s)
            throws ParseException {
        return parser.parse(s);
    }

    public static class Parser
            extends AbstractParser<Provider> {

        @Override
        public Provider parse(String s, IOptions options)
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
            IParser<? extends Provider> parser = parsers.get(type);
            if (parser == null)
                throw new ParseException(nls.tr("No such provider: ") + type);
            return parser.parse(s);
        }

    }

    static TextMap<IParser<? extends Provider>> parsers;
    static {
        parsers = new HashTextMap<>();

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
        // parsers.put("PKCS11", new PKCS11Parser());

        parsers.put("bcprov", new BcprovParser());
    }

    static class BcprovParser
            extends AbstractParser<BouncyCastleProvider> {

        TempOut getTemp()
                throws IOException {
            File tempFile = File.createTempFile("bcconf", ".ini");
            Charset utf8 = Charsets.UTF_8;
            TempOut tempOut = new TempOut(tempFile, utf8);
            return tempOut;
        }

        boolean hasToken(Provider provider) {
            // has any token?
            Service service = provider.getService("KeyStore", "PKCS11");
            return service != null;
        }

        @Override
        public BouncyCastleProvider parse(String s, IOptions options)
                throws ParseException {
            CURL curl = new CURL(s);
            if (curl.getType() != null)
                throw new ParseException(nls.tr("the type should be stripped."));
            Alpha[] alphas = curl.getAlphas();
            String path = alphas[0].formatBetas();
            BouncyCastleProvider provider = null;
            return provider;
        }
    }

}
