package net.bodz.bas.t.variant;

import java.util.Map;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.source.SerializableForm;

@SerializableForm
public interface IVarMapForm {

    /**
     * @return Reference to <code>this</code>.
     */
    void readObject(IVariantMap<String> map)
            throws ParseException;

    void writeObject(Map<String, Object> map);

}
