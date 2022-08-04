package net.bodz.bas.t.catalog;

import net.bodz.bas.repr.form.SortOrder;

public class CrossReferenceMap
        extends ListMap_JX<TableId, CrossReference> {

    public CrossReferenceMap() {
        this(SortOrder.NONE);
    }

    public CrossReferenceMap(SortOrder order) {
        super(TableId.class, () -> new CrossReference(), order);
    }

}
