package net.bodz.bas.t.catalog;

import net.bodz.bas.repr.form.SortOrder;

public class CrossReferenceMap
        extends ListMap_JX<TableOid, CrossReference> {

    public CrossReferenceMap() {
        this(SortOrder.NONE);
    }

    public CrossReferenceMap(SortOrder order) {
        super(TableOid.class, () -> new CrossReference(), order);
    }

}
