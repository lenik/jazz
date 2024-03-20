package net.bodz.lily.concrete;

import javax.persistence.GeneratedValue;

import net.bodz.lily.entity.IdColumn;
import net.bodz.lily.meta.TypeParamType;
import net.bodz.lily.meta.TypeParameters;

@TypeParameters({ TypeParamType.ID_TYPE })
@IdColumn("id")
public abstract class IdEntity<Id>
        extends CoEntity<Id> {

    private static final long serialVersionUID = 1L;

    @GeneratedValue
    public Id getId() {
        return id();
    }

    public void setId(Id id) {
        id(id);
    }

}
