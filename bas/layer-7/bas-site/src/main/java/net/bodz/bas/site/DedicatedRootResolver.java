package net.bodz.bas.site;

import javax.servlet.http.HttpServletRequest;

public class DedicatedRootResolver
        implements
            ISiteRootResolver {

    final ISiteRoot root;

    public DedicatedRootResolver(ISiteRoot root) {
        if (root == null)
            throw new NullPointerException("root");
        this.root = root;
    }

    @Override
    public ISiteRoot getSiteRoot(HttpServletRequest request) {
        return root;
    }

}
