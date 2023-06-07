package net.bodz.lily.contact.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.contact.PartyCategory;
import net.bodz.lily.model.base.CoIndex;

/**
* @label PartyCategory
*/
@ObjectType(PartyCategory.class)
public class PartyCategoryIndex
        extends CoIndex<PartyCategory, PartyCategoryMask> {

    public PartyCategoryIndex() {
    }

}