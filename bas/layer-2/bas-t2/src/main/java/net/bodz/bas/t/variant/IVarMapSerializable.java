package net.bodz.bas.t.variant;

import java.util.Map;

import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.source.SerializableForm;

@SerializableForm
public interface IVarMapSerializable {

    void readObject(IVariantMap<String> map)
            throws LoaderException, ParseException;

    void writeObject(Map<String, Object> map);

}
