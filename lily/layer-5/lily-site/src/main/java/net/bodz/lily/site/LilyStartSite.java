package net.bodz.lily.site;

import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.site.BasicSite;
import net.bodz.bas.site.org.ICrawler;

public abstract class LilyStartSite
        extends BasicSite {

    public LilyStartSite() {
    }

    @Override
    public synchronized IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens)
            throws PathDispatchException {
        return super.dispatch(previous, tokens);
    }

    @Override
    public void crawlableIntrospect(ICrawler crawler) {
        // crawler.follow("TODO", null);
    }

}
