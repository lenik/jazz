package net.bodz.lily.model.base;

import net.bodz.bas.err.ParseException;

import net.bodz.lily.model.sea.QVariantMap;

public class CoEntity<Id>
        extends CoObject
        implements IId<Id> {

    private static final long serialVersionUID = 1L;

    private Id id;

    @Override
    public Class<Id> idType() {
        return IId.fn._getIdType(getClass());
    }

    @Override
    public Id getId() {
        return id;
    }

    @Override
    public void setId(Id id) {
        this.id = id;
    }

    @Override
    protected void populate(QVariantMap<String> map)
            throws ParseException {
        super.populate(map);
        // XXX id = map.getInt("id", id);
    }

}
