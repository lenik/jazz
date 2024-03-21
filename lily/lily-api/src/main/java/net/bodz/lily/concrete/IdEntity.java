package net.bodz.lily.concrete;

import javax.persistence.GeneratedValue;

import net.bodz.lily.entity.IdColumn;
import net.bodz.lily.meta.FieldGroupVue;
import net.bodz.lily.meta.ReadOnly;
import net.bodz.lily.meta.TsTyped;
import net.bodz.lily.meta.TypeParamType;
import net.bodz.lily.meta.TypeParameters;

@FieldGroupVue
@IdColumn("id")
@TsTyped
@TypeParameters({ TypeParamType.ID_TYPE })
public abstract class IdEntity<Id>
        extends CoEntity<Id> {

    private static final long serialVersionUID = 1L;

    @ReadOnly
    @GeneratedValue
    public Id getId() {
        return id();
    }

    public void setId(Id id) {
        id(id);
    }

}
