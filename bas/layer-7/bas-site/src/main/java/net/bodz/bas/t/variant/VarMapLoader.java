package net.bodz.bas.t.variant;

import java.util.*;

import net.bodz.bas.c.primitive.Primitives;
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
    boolean skipNull = true;

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
        if (anyVal == null)
            if (skipNull)
                return;

        int pos = path.indexOf(fieldSep);
        String remain = null;
        if (pos != -1) {
            remain = path.substring(pos + 1);
            path = path.substring(0, pos);
        }

        IProperty property = type.getProperty(path);
        if (property == null) {
            // nothing yet.
            // logger.warn("Ignored non-existed property: " + path);
            return;
        }

        if (remain == null) {
            if (!property.isWritable()) {
                // can be transient or derived, and all
                // logger.warn("Ignored readonly property: " + path);
                return;
            }
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

    public void loadProperty(Object obj, IProperty property, Object rval)
            throws LoaderException {
        if (obj == null)
            throw new NullPointerException("obj");
        if (property == null)
            throw new NullPointerException("property");
        Class<?> ltype = property.getPropertyType();
        ltype = Primitives.box(ltype);
        Class<?> rtype = rval.getClass();

        Object lval;
        do {
            if (ltype == rtype) {
                lval = rval;
                break;
            }

            IVarConverter<Object> rconv = VarConverters.getConverter(rtype);
            if (rconv != null && rconv.canConvertTo(ltype)) {
                lval = rconv.to(rval, ltype);
                break;
            }

            IVarConverter<Object> lconv = VarConverters.getConverter(ltype);
            if (lconv != null && lconv.canConvertFrom(rtype)) {
                lval = lconv.from(rtype, rval);
                break;
            }

            // Load collections.
            try {
                lval = property.getValue(obj);
            } catch (ReflectiveOperationException e) {
                throw new LoaderException("Failed to access object property: " + e.getMessage(), e);
            }

            if (rval instanceof ILookupMap) {
                ILookupMap<String, ?> rmap = ILookupMap.class.cast(rval);
                if (lval == null) {
                    if (!autoCreate) {
                        logger.warn("Skipped to load nested map on null property.");
                        return;
                    }
                    try {
                        lval = ltype.newInstance();
                    } catch (ReflectiveOperationException e) {
                        throw new LoaderException("Failed to auto create: " + e.getMessage(), e);
                    }
                }
                load(ltype, lval, rmap);
                break;
            }

            if (rval instanceof List<?>) {
                Collection<Object> coll = newConcrete(ltype);
                List<?> rlist = (List<?>) rval;
                for (Object r : rlist)
                    coll.add(r);
                lval = coll;
                break;
            }

            logger.warn(String.format(//
                    "No converter for property %s from %s: %s.", property, rtype, rval));
            return;
        } while (false);

        try {
            property.setValue(obj, lval);
        } catch (ReflectiveOperationException e) {
            throw new LoaderException("Failed to set property value: " + e.getMessage(), e);
        }
    }

    static Map<Class<?>, Class<?>> concreteTypes = new HashMap<>();
    static {
        concreteTypes.put(List.class, ArrayList.class);
        concreteTypes.put(Set.class, HashSet.class);
        concreteTypes.put(NavigableSet.class, TreeSet.class);
    }

    static Collection<Object> newConcrete(Class<?> generic) {
        Class<?> concreteType = concreteTypes.get(generic);
        if (concreteType != null)
            try {
                return (Collection<Object>) concreteType.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        return null;
    }

}
