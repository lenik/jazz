package net.bodz.lily.model.base;

import java.io.IOException;
import java.io.Serializable;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonObject;
import net.bodz.bas.repr.form.meta.FormInput;
import net.bodz.bas.t.variant.conv.IVarConverter;
import net.bodz.bas.t.variant.conv.VarConverters;
import net.bodz.lily.entity.IId;
import net.bodz.lily.entity.IdAwares;

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
        return IdAwares._getIdType(getClass());
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
    public void setId_(Serializable id) {
        setId((Id) id);
    }

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        super.readObject(o);

        Object _id = o._get("id");
        if (_id != null) {
            IVarConverter<Id> idConv = VarConverters.getConverter(idType());
            Id newId = idConv.from(o._get("id"));
            this.id = newId;
        }
    }

    @Override
    public void writeObject(IJsonOut out)
            throws IOException {
        super.writeObject(out);
    }

}
