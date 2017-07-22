package net.bodz.bas.fmt.json.obj;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.beans.Transient;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import net.bodz.bas.c.type.TypeId;
import net.bodz.bas.c.type.TypeKind;
import net.bodz.bas.c.type.TypeParam;
import net.bodz.bas.err.LoadException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.AbstractJsonLoader;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.t.variant.MutableVariant;
import net.bodz.bas.typer.Typers;
import net.bodz.bas.typer.std.IParser;

public class BeanJsonLoader
        extends AbstractJsonLoader {

    static final Logger logger = LoggerFactory.getLogger(BeanJsonLoader.class);

    public void parse(Object obj, String json)
            throws ParseException {
        try {
            JSONObject node = new JSONObject(json);
            parse(obj, node);
        } catch (JSONException e) {
            throw new ParseException("Failed to parse JSON: " + e.getMessage(), e);
        }
    }

    public void parse(Object obj, JSONObject node)
            throws ParseException {
        try {
            new BeanJsonLoader().load(obj, node);
        } catch (ParseException e) {
            throw e;
        } catch (Exception e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

    @Override
    public void load(Object ctx, JSONObject node)
            throws Exception {
        Class<?> ctxType = ctx.getClass();
        BeanInfo beanInfo;
        try {
            beanInfo = Introspector.getBeanInfo(ctxType);
        } catch (IntrospectionException e) {
            throw new LoadException(String.format("Failed to get bean info of %s: %s.", ctxType, e.getMessage()), e);
        }

        for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
            Class<?> propertyType = propertyDescriptor.getPropertyType();
            Method getter = propertyDescriptor.getReadMethod();
            Method setter = propertyDescriptor.getWriteMethod();
            if (getter == null)
                continue;

            if (getter.isAnnotationPresent(Transient.class))
                continue;

            String propertyName = propertyDescriptor.getName();
            if (!node.has(propertyName))
                continue;
            if ("class".equals(propertyName))
                continue;

            Object propertyValue;
            try {
                propertyValue = getter.invoke(ctx);
            } catch (ReflectiveOperationException e) {
                logger.error(e, "Failed to invoke getter: " + e.getMessage());
                continue;
            }
            Object jsonVal = node.get(propertyName);

            try {
                if (jsonVal == null) {
                    if (setter == null)
                        logger.warn("Set null on read-only property: " + propertyDescriptor);
                    else
                        setter.invoke(ctx, new Object[] { null });
                    continue;
                }

                Object value = convert(propertyType, propertyValue, jsonVal);
                if (value != NonTerm) {
                    if (setter != null)
                        setter.invoke(ctx, new Object[] { value });
                    continue;
                }

                if (!(jsonVal instanceof JSONObject)) {
                    // Should be terminated.
                    continue;
                }

                Object obj = propertyValue;
                boolean create = setter == null;
                Constructor<?> ctor0 = null;
                if (create)
                    try {
                        ctor0 = propertyType.getConstructor();
                    } catch (NoSuchMethodException e) {
                        create = false;
                    }

                if (create)
                    obj = ctor0.newInstance();
                else {
                    if (obj == null)
                        continue;
                }

                JSONObject jsonObj = (JSONObject) jsonVal;
                load(obj, jsonObj);

                if (create)
                    setter.invoke(ctx, obj);
            } catch (ReflectiveOperationException e) {
                logger.error(e, "Failed to invoke setter: " + e.getMessage());
                continue;
            }
        }
    }

    static final Object NonTerm = new Object();

    Object convert(Class<?> type, Object orig, Object jsonVal)
            throws ReflectiveOperationException, ParseException {
        MutableVariant jsonVar = MutableVariant.wrap(jsonVal);
        {
            switch (TypeKind.getTypeId(type)) {
            case TypeId._char:
            case TypeId.CHARACTER:
                return jsonVar.getChar();
            case TypeId._byte:
            case TypeId.BYTE:
                return jsonVar.getByte();
            case TypeId._short:
            case TypeId.SHORT:
                return jsonVar.getShort();
            case TypeId._int:
            case TypeId.INTEGER:
                return jsonVar.getInt();
            case TypeId._long:
            case TypeId.LONG:
                return jsonVar.getLong();
            case TypeId._float:
            case TypeId.FLOAT:
                return jsonVar.getFloat();
            case TypeId._double:
            case TypeId.DOUBLE:
                return jsonVar.getDouble();
            case TypeId._boolean:
            case TypeId.BOOLEAN:
                return jsonVar.getBoolean();
            case TypeId.BIG_INTEGER:
                return jsonVar.getBigInteger();
            case TypeId.BIG_DECIMAL:
                return jsonVar.getBigDecimal();
            case TypeId.DATE:
                return jsonVar.getDate();
            case TypeId.SQL_DATE:
                return new java.sql.Date(jsonVar.getDate().getTime());
            case TypeId.STRING:
                return jsonVar.toString();
            }
        }

        if (type.isArray()) {
            Class<?> ctype = type.getComponentType();
            JSONArray jsonArray = (JSONArray) jsonVal;
            int len = jsonArray.length();
            Object array = Array.newInstance(ctype, len);
            for (int i = 0; i < len; i++) {
                Object item = convert(ctype, null, jsonArray.get(i));
                Array.set(array, i, item);
            }
            return array;
        }

        if (Collection.class.isAssignableFrom(type)) {
            Class<?> ctype = TypeParam.infer1(type, Collection.class, 0);
            Collection<Object> coll = null;
            try {
                Constructor<?> ctor0 = type.getConstructor();
                coll = (Collection<Object>) ctor0.newInstance();
            } catch (NoSuchMethodException e) {
                if (List.class.isAssignableFrom(type))
                    coll = new ArrayList<>();
                else if (Set.class.isAssignableFrom(type))
                    coll = new LinkedHashSet<>();
                else if (Queue.class.isAssignableFrom(type))
                    coll = new LinkedBlockingDeque<>();
            }
            if (coll != null) {
                JSONArray jsonArray = (JSONArray) jsonVal;
                int len = jsonArray.length();
                for (int i = 0; i < len; i++) {
                    Object item = convert(ctype, null, jsonArray.get(i));
                    coll.add(item);
                }
                return coll;
            }
        }

        if (Map.class.isAssignableFrom(type)) {
            Class<?> ktype = TypeParam.infer1(type, Map.class, 0);
            Class<?> vtype = TypeParam.infer1(type, Map.class, 1);
            Map<String, Object> map = null;
            do {
                if (ktype != String.class)
                    break;
                try {
                    Constructor<?> ctor0 = type.getConstructor();
                    map = (Map<String, Object>) ctor0.newInstance();
                } catch (NoSuchMethodException e) {
                    map = new HashMap<>();
                }
                if (map != null) {
                    JSONObject jsonMap = (JSONObject) jsonVal;
                    for (String key : (Set<String>) jsonMap.keySet()) {
                        Object jsonItemVal = jsonMap.get(key);
                        Object item = convert(vtype, null, jsonItemVal);
                        map.put(key, item);
                    }
                    return map;
                }
            } while (false);
        }

        if (jsonVal.getClass() == String.class) {
            String str = (String) jsonVal;
            IParser<?> parser = Typers.getTyper(type, IParser.class);
            if (parser != null) {
                return parser.parse(str);
            }
        }

        return NonTerm;
    }

    static BeanJsonLoader instance = new BeanJsonLoader();

    public static BeanJsonLoader getInstance() {
        return instance;
    }

}
