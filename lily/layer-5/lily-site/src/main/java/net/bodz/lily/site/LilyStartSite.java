package net.bodz.lily.site;

import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.site.BasicSite;
import net.bodz.bas.site.org.ICrawler;

import net.bodz.lily.model.mx.forum.Forum;
import net.bodz.lily.model.mx.track.IssueTracker;

public abstract class LilyStartSite
        extends BasicSite {

    Forum forum;
    IssueTracker tracker;

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
