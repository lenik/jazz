package net.bodz.lily.concrete;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.t.variant.AbstractMutableVariant;
import net.bodz.bas.t.variant.IVarAttributes;
import net.bodz.bas.t.variant.IVariant;

public interface IAttrInProps
        extends
            IHaveProperties,
            IVarAttributes {

    @Override
    default IVariant getAttribute(String name) {
        JsonVariant properties = getProperties();
        if (properties == null)
            return null;
        JsonVariant jv = properties.get(name);
        if (jv == null)
            return IVariant.NULL;

        return new AbstractMutableVariant() {

            @Override
            public Object get() {
                return jv.getScalar();
            }

            @Override
            public void set(Object value) {
                JsonObject properties = properties();
                properties.put(name, value);
            }

        };
    }

    @Override
    default void setAttribute(String name, Object value) {
        JsonObject properties = properties();
        properties.put(name, value);
    }

}
