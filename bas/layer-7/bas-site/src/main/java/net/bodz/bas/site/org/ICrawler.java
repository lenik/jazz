package net.bodz.bas.site.org;

import net.bodz.bas.repr.content.IContent;

public interface ICrawler {

    void follow(String subpath, IContent content);

}
