package net.bodz.lily.concrete;

import net.bodz.lily.entity.IdColumn;

@IdColumn("id")
public abstract class IdEntity<Id>
        extends CoEntity<Id> {

    private static final long serialVersionUID = 1L;

    public Id getId() {
        return id();
    }

    public void setId(Id id) {
        id(id);
    }

}
