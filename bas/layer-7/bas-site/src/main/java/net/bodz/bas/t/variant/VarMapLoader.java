package net.bodz.bas.t.variant;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

import net.bodz.bas.c.primitive.Primitives;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
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

            if (key.endsWith("[]"))
                key = key.substring(0, key.length() - 2);

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
            if (! property.isWritable()) {
                // can be transient or derived, and all
                // logger.warn("Ignored readonly property: " + path);
                return;
            }
            loadProperty(obj, property, anyVal);
            return;
        }
        if ("null".equals(remain)) {
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
        Class<?> pClass = property.getPropertyClass();
        IType pType = types.loadType(pClass, pVal);

        boolean dirty = false;
        if (pVal == null) {
            if (! autoCreate)
                return;
            try {
                pVal = pClass.getConstructor().newInstance();
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
        Class<?> ltype = property.getPropertyClass();

//        Type gtype = property.getPropertyGenericType();

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
                    if (! autoCreate) {
                        logger.warn("Skipped to load nested map on null property.");
                        return;
                    }
                    try {
                        lval = ltype.getConstructor().newInstance();
                    } catch (ReflectiveOperationException e) {
                        throw new LoaderException("Failed to auto create: " + e.getMessage(), e);
                    }
                }
                load(ltype, lval, rmap);
                break;
            }

            if (rval instanceof List<?>) {
                logger.warn("Not supported yet");
//
// Collection<Object> coll = newConcrete(ltype);
// List<?> rlist = (List<?>) rval;
// for (Object r : rlist) {
// // TODO unwrap this object? convert to ltype-itemtype?
// coll.add(r);
// }
// lval = coll;
// break;
            }

            logger.warn(String.format(//
                    "No converter for property '%s': from value %s (of type %s).", //
                    property, //
                    format(rval), rtype.getCanonicalName()));
            return;
        } while (false);

        try {
            property.setValue(obj, lval);
        } catch (ReflectiveOperationException e) {
            throw new LoaderException("Failed to set property value: " + e.getMessage(), e);
        }
    }

    static String format(Object o) {
        if (o == null)
            return "null";
        if (o.getClass().isArray()) {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            int n = Array.getLength(o);
            for (int i = 0; i < n; i++) {
                if (i != 0)
                    sb.append(", ");
                String item = format(Array.get(o, i));
                sb.append(item);
            }
            sb.append("]");
            return sb.toString();
        } else
            return o.toString();
    }

    static Map<Class<?>, Class<?>> concreteTypes = new HashMap<Class<?>, Class<?>>();
    static {
        concreteTypes.put(List.class, ArrayList.class);
        concreteTypes.put(Set.class, HashSet.class);
        concreteTypes.put(NavigableSet.class, TreeSet.class);
    }

    @SuppressWarnings("unchecked")
    static Collection<Object> newConcrete(Class<?> generic) {
        Class<?> concreteType = concreteTypes.get(generic);
        if (concreteType != null)
            try {
                return (Collection<Object>) concreteType.getConstructor().newInstance();
            } catch (ReflectiveOperationException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        return null;
    }

    public static void defaultParse(Object o, IVariantMap<String> q)
            throws ParseException {
        VarMapLoader loader = new VarMapLoader();
        Class<?> type = o.getClass();
        try {
            loader.load(type, o, q);
        } catch (LoaderException e) {
            throw new ParseException("Error load " + type + ": " + e.getMessage(), e);
        }
    }

}
