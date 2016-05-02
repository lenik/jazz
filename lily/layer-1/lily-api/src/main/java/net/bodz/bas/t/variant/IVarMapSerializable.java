package net.bodz.bas.t.variant;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.source.SerializableForm;

@SerializableForm
public interface IVarMapSerializable {

    void readObject(IVariantMap<String> map)
            throws ParseException;

    void writeObject(IVariantMap<String> map);

}
