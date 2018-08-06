package net.bodz.lily.contact;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.template.CoCategory;

@IdType(Integer.class)
public class PartyCategory
        extends CoCategory<PartyCategory, Integer> {

    private static final long serialVersionUID = 1L;

    public PartyCategory() {
    }

    public PartyCategory(PartyCategory parent) {
        super(parent);
    }

}
