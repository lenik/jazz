package net.bodz.bas.repr.content;

import net.bodz.bas.std.rfc.http.ICacheControl;
import net.bodz.bas.t.order.IPriority;

/**
 * @label Content
 * @label.zh.cn 内容
 */
public interface IContent
        extends ICacheControl, IPriority {

    long getCreationTime();

}
