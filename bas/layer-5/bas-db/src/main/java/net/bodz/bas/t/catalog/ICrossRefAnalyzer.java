package net.bodz.bas.t.catalog;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface ICrossRefAnalyzer {

    default List<CrossReference> findCrossReferences(TableOid parent) {
        List<CrossReference> list = new ArrayList<>();
        findCrossReferences(list, parent);
        return list;
    }

    void findCrossReferences(Collection<CrossReference> list, TableOid parent);

}
