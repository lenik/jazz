package net.bodz.swt.gui.pfl;

import java.util.Map.Entry;

import net.bodz.bas.io.CharOuts.BCharOut;
import net.bodz.bas.types.TextMap;
import net.bodz.bas.types.TextMap.TreeTextMap;

public class SymlinkPageFlow extends PageFlow {

    public static final int maxDepth = 16;

    private TextMap<String> symlinks;

    public SymlinkPageFlow() {
        symlinks = new TreeTextMap<String>();
    }

    @Override
    public String normalize(String address) {
        if (address == null)
            return null;
        int depth = 0;
        do {
            if (isPageLoaded(address))
                return address;
            if (isPageLoadable(address))
                return address;
            String symlink = symlinks.get(address);
            if (symlink != null) {
                address = symlink;
                continue;
            }
            return null;
        } while (++depth < maxDepth);
        throw new IllegalStateException("exceeds the max symlink depth: "
                + depth);
    }

    public void putLink(String name, String address) {
        symlinks.put(name, address);
    }

    public void removeLink(String name) {
        symlinks.remove(name);
    }

    @Override
    public String toString() {
        BCharOut buf = new BCharOut(symlinks.size() * 30);
        buf.println("<symlink>");
        for (Entry<String, String> e : symlinks.entrySet()) {
            buf.println("  " + e.getKey() + " -> " + e.getValue());
        }
        buf.println("</symlink>");
        return buf.toString();
    }

}
