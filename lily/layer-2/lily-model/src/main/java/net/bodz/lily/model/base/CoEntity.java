package net.bodz.lily.model.base;

import net.bodz.bas.c.type.TypeId;
import net.bodz.bas.c.type.TypeKind;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.repr.form.meta.FormInput;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.bas.t.variant.MutableVariant;
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

    @SuppressWarnings("unchecked")
    @Override
    public void readObject(IVariantMap<String> map)
            throws ParseException {
        super.readObject(map);

        Object id = map.get("id");
        MutableVariant idVar = MutableVariant.wrap(id);
        switch (TypeKind.getTypeId(idType())) {
        case TypeId.INTEGER:
            id = (Id) (Integer) idVar.getInt();
            break;
        case TypeId.LONG:
            id = (Id) (Long) idVar.getLong();
            break;
        case TypeId.STRING:
            id = (Id) (String) idVar.getString();
            break;
        default:
            throw new NotImplementedException();
        }
    }

}
