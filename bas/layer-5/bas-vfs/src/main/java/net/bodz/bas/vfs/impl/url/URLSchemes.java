package net.bodz.bas.vfs.impl.url;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class URLSchemes {

    /**
     * VFS-Protocol => JDK-Protocol
     * 
     * These protocol names are found in the package name: sun.net.www.protocol.*.Handler
     * 
     * See also: sun.net.www.protocol.jar.Handler
     */
    static final Set<String> schemeNames;
    static final Map<String, URLFormat> schemeFormatMap;

    static {
        schemeFormatMap = new HashMap<String, URLFormat>();

        schemeFormatMap.put("file", URLFormat.plain);
        schemeFormatMap.put("ftp", URLFormat.hostPathQuery);
        schemeFormatMap.put("gopher", URLFormat.hostPathQuery);
        schemeFormatMap.put("http", URLFormat.hostPathQuery);
        schemeFormatMap.put("https", URLFormat.hostPathQuery);
        schemeFormatMap.put("jar", URLFormat.nestedPath);
        schemeFormatMap.put("mailto", URLFormat.hostQuery);
        schemeFormatMap.put("netdoc", URLFormat.hostPathQuery);

        schemeNames = schemeFormatMap.keySet();
    }

    public static Set<String> getSchemeNames() {
        return schemeNames;
    }

    public static URLFormat getSchemeFormat(String scheme) {
        return schemeFormatMap.get(scheme);
    }

}
