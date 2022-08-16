package net.bodz.bas.t.catalog;

import net.bodz.bas.err.DuplicatedKeyException;

public interface ITableViewDirectory {

    default TableViewList findViews(TableOid pattern) {
        return findViews(pattern, false);
    }

    TableViewList findViews(TableOid pattern, boolean ignoreCase);

    default ITableViewMetadata getView(TableOid pattern) {
        return getView(pattern, false);
    }

    default ITableViewMetadata getView(TableOid pattern, boolean ignoreCase) {
        TableViewList viewList = findViews(pattern, ignoreCase);
        if (viewList.size() > 1)
            throw new DuplicatedKeyException("More than single table matched: " + pattern);
        if (viewList.isEmpty())
            return null;
        else
            return viewList.get(0);
    }

}
