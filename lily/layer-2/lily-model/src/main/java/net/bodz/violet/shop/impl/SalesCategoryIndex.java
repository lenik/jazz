package net.bodz.violet.shop.impl;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoCategoryIndex;
import net.bodz.violet.shop.SalesCategory;

@ObjectType(SalesCategory.class)
public class SalesCategoryIndex
        extends CoCategoryIndex<SalesCategory, SalesCategoryMask> {

    public static final String SCHEMA = "salescat";

    public SalesCategoryIndex() {
        super(SCHEMA);
    }

}
