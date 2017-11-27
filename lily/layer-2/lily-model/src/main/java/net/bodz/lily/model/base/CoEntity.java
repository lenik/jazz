package net.bodz.lily.model.base;

import net.bodz.bas.repr.form.meta.FormInput;
import net.bodz.lily.entity.IId;

/**
 * aka. Common Entity.
 */
public class CoEntity<Id>
        extends CoObject
        implements IId<Id> {

    private static final long serialVersionUID = 1L;

    private Id id;

    @Override
    public Class<Id> idType() {
        return IId.fn._getIdType(getClass());
    }

    @FormInput(readOnly = true)
    @Override
    public Id getId() {
        return id;
    }

    @Override
    public void setId(Id id) {
        this.id = id;
    }

}
