package net.bodz.bas.t.catalog;

import java.util.List;

import net.bodz.bas.err.DuplicatedKeyException;

public interface IViewDirectory {

    default List<IViewMetadata> findViews(TableOid pattern) {
        return findViews(pattern, false);
    }

    List<IViewMetadata> findViews(TableOid pattern, boolean ignoreCase);

    default ITableMetadata getView(TableOid pattern) {
        return getView(pattern, false);
    }

    default ITableMetadata getView(TableOid pattern, boolean ignoreCase) {
        List<IViewMetadata> viewList = findViews(pattern, ignoreCase);
        if (viewList.size() > 1)
            throw new DuplicatedKeyException("More than single table matched: " + pattern);
        if (viewList.isEmpty())
            return null;
        else
            return viewList.get(0);
    }

}
