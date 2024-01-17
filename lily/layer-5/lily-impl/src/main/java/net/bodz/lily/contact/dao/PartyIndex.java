package net.bodz.lily.contact.dao;

import net.bodz.lily.contact.Party;
import net.bodz.lily.model.base.CoIndex;

public abstract class PartyIndex<T extends Party, M extends PartyCriteriaBuilder>
        extends CoIndex<T, M> {

    private String schema;

    public PartyIndex(String schema) {
        this.schema = schema;
    }

}
