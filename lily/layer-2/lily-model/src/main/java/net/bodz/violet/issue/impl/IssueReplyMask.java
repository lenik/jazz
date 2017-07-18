package net.bodz.violet.issue.impl;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.model.mx.CoMessageMask;
import net.bodz.violet.issue.IssueReply;

/**
 * @see IssueReply
 */
public class IssueReplyMask
        extends CoMessageMask {

    @Override
    public void readObject(IVariantMap<String> _map)
            throws ParseException {
        super.readObject(_map);

        // QVariantMap<String> map = QVariantMap.from(_map);
    }

}
