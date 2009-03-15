package net.bodz.swt.gui.pfl;

import java.io.Serializable;

import net.bodz.bas.types.TextMap;
import net.bodz.bas.types.TextMap.TreeTextMap;

public class PageFlowConfig implements Serializable {

    private static final long serialVersionUID = -130771562862264322L;

    /**
     * unix-style path -> page object
     */
    private TextMap<Page>     pages;
    private TextMap<String>   symlinks;

    public PageFlowConfig() {
        pages = new TreeTextMap<Page>();
        symlinks = new TreeTextMap<String>();
    }

    static final int maxDepth = 16;

    /**
     * when both link and symlink exists, link is preferred.
     * 
     * @return <code>null</code> if <code>path</code> is <code>null</code>.
     */
    public Page getPage(String path) {
        if (path == null)
            return null;
        int depth = 0;
        do {
            Page page = pages.get(path);
            if (page != null)
                return page;
            path = symlinks.get(path);
            if (path == null)
                return null;
        } while (++depth < maxDepth);
        throw new IllegalStateException("exceeds the max symlink depth: "
                + depth);
    }

    public Page getNextPage(String fromPath, String relative) {
        String path = join(fromPath, relative);
        return getPage(path);
    }

    public void putPage(String path, Page page) {
        // if (pages.containsKey(path))
        pages.put(path, page);
    }

    String join(String path, String relative) {
        if (relative.startsWith("/"))
            return relative;
        return path + "/" + relative;
    }

    public void link(String fromPath, String relative, Page nextPage) {
        String path = join(fromPath, relative);
        pages.put(path, nextPage);
    }

    public void symlink(String fromPath, String relative, String nextPath) {
        String path = join(fromPath, relative);
        symlinks.put(path, nextPath);
    }

}
