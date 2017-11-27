package net.bodz.bas.t.variant;

import net.bodz.bas.err.LoaderException;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.potato.ITypeProvider;
import net.bodz.bas.potato.PotatoTypes;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.t.variant.conv.IVarConverter;
import net.bodz.bas.t.variant.conv.VarConverters;

public class VarMapLoader {

    static final Logger logger = LoggerFactory.getLogger(VarMapLoader.class);

    static ITypeProvider types = PotatoTypes.getInstance();

    boolean autoCreate = true;
    char fieldSep = '.';

    public void load(Class<?> clazz, Object obj, ILookupMap<String, ?> map)
            throws LoaderException {
        if (clazz == null)
            throw new NullPointerException("clazz");
        if (obj == null)
            throw new NullPointerException("obj");
        if (map == null)
            throw new NullPointerException("map");
        IType type = types.loadType(clazz, obj);
        for (String key : map.keySet()) {
            Object val = map.get(key);
            load(type, obj, key, val);
        }
    }

    void load(IType type, Object obj, String path, Object anyVal)
            throws LoaderException {
        int pos = path.indexOf(fieldSep);
        String remain = null;
        if (pos != -1) {
            remain = path.substring(pos + 1);
            path = path.substring(0, pos);
        }

        IProperty property = type.getProperty(path);
        if (property == null) {
            // nothing yet.
            logger.warn("Ignored non-existed property: " + path);
            return;
        }

        if (remain == null) {
            loadProperty(obj, property, anyVal);
            return;
        }
        switch (remain) {
        case "null":
            // if (anyVal)
            try {
                property.setValue(obj, null);
            } catch (ReflectiveOperationException e) {
                throw new LoaderException("Failed to set to null: " + e.getMessage(), e);
            }
            return;
        }

        Object pVal;
        try {
            pVal = property.getValue(obj);
        } catch (ReflectiveOperationException e) {
            throw new LoaderException("Failed to access object property: " + e.getMessage(), e);
        }
        Class<?> pClass = property.getPropertyType();
        IType pType = types.loadType(pClass, pVal);

        boolean dirty = false;
        if (pVal == null) {
            if (!autoCreate)
                return;
            try {
                pVal = pClass.newInstance();
            } catch (ReflectiveOperationException e) {
                throw new LoaderException("Failed to auto create: " + e.getMessage(), e);
            }
            dirty = true;
        }
        load(pType, pVal, remain, anyVal);
        if (dirty)
            try {
                property.setValue(obj, pVal);
            } catch (ReflectiveOperationException e) {
                throw new LoaderException("Failed to save auto created instance: " + e.getMessage(), e);
            }
    }

    public void loadProperty(Object obj, IProperty property, Object anyVal)
            throws LoaderException {
        if (obj == null)
            throw new NullPointerException("obj");
        if (property == null)
            throw new NullPointerException("property");
        Class<?> ltype = property.getPropertyType();
        Class<?> rtype = anyVal.getClass();
        IVarConverter<Object> converter = (IVarConverter<Object>) VarConverters.getConverter(rtype);
        Object lval = converter.to(anyVal, ltype);
        try {
            property.setValue(obj, lval);
        } catch (ReflectiveOperationException e) {
            throw new LoaderException("Failed to set property value: " + e.getMessage(), e);
        }
    }

}
