package net.bodz.swt.gui.pfl;

import net.bodz.bas.types.TextMap;
import net.bodz.bas.types.TextMap.TreeTextMap;

public class SymlinkPageFlow extends PageFlow {

    private TextMap<String>  symlinks = new TreeTextMap<String>(); ;

    private static final int maxDepth = 16;

    public SymlinkPageFlow() {
        super();
    }

    public SymlinkPageFlow(int maxHistory) {
        super(maxHistory);
    }

    @Override
    public Page getPage(String path) {
        if (path == null)
            return null;
        int depth = 0;
        do {
            if (super.isPageLoaded(path))
                return super.getPage(path);
            String symlink = symlinks.get(path);
            if (symlink != null) {
                path = symlink;
                continue;
            }
            Page lazyCreatedPage = super.getPage(path);
            if (lazyCreatedPage != null)
                return lazyCreatedPage;
            return null;
        } while (++depth < maxDepth);
        throw new IllegalStateException("exceeds the max symlink depth: "
                + depth);
    }

    public void putLink(String linkName, String path) {
        symlinks.put(linkName, path);
    }

    public void removeLink(String linkName) {
        symlinks.remove(linkName);
    }

    protected String deref(String path) {
        String target = path;
        while (true) {
            target = symlinks.get(path);
            if (target == null)
                break;
            path = target;
        }
        return path;
    }

    /**
     * symlinks are dereferenced if necessary.
     */
    @Override
    public void setCurrentPath(String newPath) {
        super.setCurrentPath(deref(newPath));
    }

}
