package net.bodz.bas.vfs.impl.jdk;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import net.bodz.bas.vfs.path.AbstractGenericPathParser;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;

public class URLPathParser
        extends AbstractGenericPathParser {

    URLVfsDevice device = URLVfsDevice.getInstance();

    /**
     * These protocol names are found in the package name:
     * 
     * sun.net.www.protocol.*.Handler
     */
    transient String[] systemSupportedProtocols = { "file", "ftp", "gopher", "http", "https", "jar", "mailto", "netdoc" };
    Set<String> protocols = new HashSet<String>();

    public URLPathParser() {
        Collections.addAll(protocols, systemSupportedProtocols);
    }

    @Override
    public boolean accepts(String protocol) {
        return protocols.contains(protocol);
    }

    @Override
    public IPath parse(String path)
            throws BadPathException {
        return device.parse(path);
    }

}
