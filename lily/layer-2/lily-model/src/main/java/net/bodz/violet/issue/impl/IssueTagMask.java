package net.bodz.violet.issue.impl;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.model.base.CoNodeMask;
import net.bodz.violet.issue.IssueTag;

/**
 * @see IssueTag
 */
public class IssueTagMask
        extends CoNodeMask {

    @Override
    public void readObject(IVariantMap<String> _map)
            throws ParseException {
        super.readObject(_map);

        // QVariantMap<String> map = QVariantMap.from(_map);
    }

}
