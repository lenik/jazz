package net.bodz.lily.concrete;

import java.io.Serializable;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.TsTyped;
import net.bodz.bas.t.variant.conv.IVarConverter;
import net.bodz.bas.t.variant.conv.VarConverters;
import net.bodz.lily.entity.IId;
import net.bodz.lily.entity.IdFn;
import net.bodz.lily.meta.FieldGroupVue;

/**
 * aka. Common Entity.
 */
@FieldGroupVue
@TsTyped
public abstract class CoEntity<Id>
        extends CoObject
        implements
            IId<Id> {

    private static final long serialVersionUID = 1L;

    @Override
    public Class<Id> idType() {
        return IdFn._getIdType(getClass());
    }

    Id __id;

    // Use id() to access the id/composite-id property.
    // Don't use get/setId() here, to reserve property `id` for general purpose.

    @Override
    public Id id() {
        return __id;
    }

    @Override
    public void id(Id id) {
        this.__id = id;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final void id_(Serializable id) {
        id((Id) id);
    }

    @Override
    public void jsonIn(@NotNull JsonObject o, JsonFormOptions opts)
            throws ParseException {
        super.jsonIn(o, opts);

        Object _id = o.get("id");
        if (_id != null) {
            IVarConverter<Id> idConv = VarConverters.getConverter(idType());
            Id newId = idConv.from(_id);
            this.id(newId);
        }
    }

    public void assign(CoEntity<Id> o) {
        super.assign(o);
        __id = o.__id;
    }

}
