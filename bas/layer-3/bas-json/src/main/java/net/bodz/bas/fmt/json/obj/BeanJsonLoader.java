package net.bodz.bas.fmt.json.obj;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

import net.bodz.bas.bean.api.IBeanInfo;
import net.bodz.bas.bean.api.IPropertyDescriptor;
import net.bodz.bas.bean.api.IntrospectionException;
import net.bodz.bas.bean.api.Introspectors;
import net.bodz.bas.c.type.TypeId;
import net.bodz.bas.c.type.TypeKind;
import net.bodz.bas.c.type.TypeParam;
import net.bodz.bas.err.LoadException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.AbstractJsonLoader;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.JsonFn;
import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.json.JsonArray;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.bean.Transient;
import net.bodz.bas.t.variant.MutableVariant;
import net.bodz.bas.typer.Typers;
import net.bodz.bas.typer.std.IParser;
import net.bodz.fork.org.json.JSONException;

public class BeanJsonLoader
        extends AbstractJsonLoader {

    static final Logger logger = LoggerFactory.getLogger(BeanJsonLoader.class);

    public void parse(Object obj, String json)
            throws ParseException {
        try {
            JsonVariant node = JsonFn.parseAny(json);
            parse(obj, node);
        } catch (JSONException e) {
            throw new ParseException("Failed to parse JSON: " + e.getMessage(), e);
        }
    }

    public void parse(Object obj, JsonVariant node)
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
    public void load(Object ctx, JsonVariant node)
            throws Exception {
        load(ctx, node, false);
    }

    public void load(Object ctx, JsonVariant node, boolean overridable)
            throws Exception {
        if (overridable) {
            if (ctx instanceof IJsonForm) {
                IJsonForm jsonForm = (IJsonForm) ctx;
                jsonForm.jsonIn(node, getOptions());
                return;
            }
        }

        Class<?> ctxType = ctx.getClass();
        IBeanInfo beanInfo;
        try {
            beanInfo = Introspectors.getBeanInfo(ctxType);
        } catch (IntrospectionException e) {
            throw new LoadException(String.format("Failed to get bean info of %s: %s.", ctxType, e.getMessage()), e);
        }

        for (IPropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
            Method getter = propertyDescriptor.getReadMethod();
            Method setter = propertyDescriptor.getWriteMethod();
            if (getter == null)
                continue;

            Class<?> propertyClass = propertyDescriptor.getPropertyType();
            // Type ReturnType = getter.getGenericReturnType();

            if (getter.isAnnotationPresent(Transient.class))
                continue;

            String propertyName = propertyDescriptor.getName();
            if ("class".equals(propertyName))
                continue;
            if (node.isObject())
                if (! node.getObject().has(propertyName))
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

                Object value = convert(propertyClass, propertyValue, jsonVal);
                if (value != NonTerm) {
                    if (setter != null)
                        setter.invoke(ctx, new Object[] { value });
                    continue;
                }

                JsonObject jsonObj;
                if (jsonVal instanceof JsonObject)
                    jsonObj = (JsonObject) jsonVal;
                else
                    continue;

                Object obj = propertyValue;
                boolean create = setter != null;
                Constructor<?> ctor0 = null;
                if (create) {
                    if (Modifier.isAbstract(propertyClass.getModifiers())) {
                        logger.error("Can't create abstract type for property " + propertyName);
                        continue;
                    }
                    try {
                        ctor0 = propertyClass.getConstructor();
                    } catch (NoSuchMethodException e) {
                        create = false;
                    }
                }

                if (create)
                    try {
                        obj = ctor0.newInstance();
                    } catch (ReflectiveOperationException e) {
                        logger.error(e, "Failed to instantiate " + propertyClass + ": " + e.getMessage());
                        continue;
                    }
                else {
                    if (obj == null)
                        continue;
                }

                load(obj, new JsonVariant(jsonObj), true);

                if (create)
                    setter.invoke(ctx, obj);
            } catch (ReflectiveOperationException e) {
                logger.error(e, "Failed to invoke setter: " + e.getMessage());
                continue;
            }
        }
    }

    static class NonTerm {
    }

    static final Object NonTerm = new NonTerm();

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

            case TypeId.INSTANT:
                return jsonVar.getInstant();
            case TypeId.ZONED_DATE_TIME:
                return jsonVar.getZonedDateTime();
            case TypeId.OFFSET_DATE_TIME:
                return jsonVar.getOffsetDateTime();
            case TypeId.LOCAL_DATE_TIME:
                return jsonVar.getLocalDateTime();
            case TypeId.LOCAL_DATE:
                return jsonVar.getLocalDate();
            case TypeId.LOCAL_TIME:
                return jsonVar.getLocalTime();

            case TypeId.STRING:
                return jsonVar.toString();
            }
        }

        if (type.isArray()) {
            Class<?> ctype = type.getComponentType();
            JsonArray jsonArray = (JsonArray) jsonVal;
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
            if (ctype == Object.class)
                // not supported.
                return NonTerm;

            Collection<Object> coll = null;
            try {
                Constructor<?> ctor0 = type.getConstructor();
                @SuppressWarnings("unchecked")
                Collection<Object> instance = (Collection<Object>) ctor0.newInstance();
                coll = instance;
            } catch (NoSuchMethodException e) {
                if (List.class.isAssignableFrom(type))
                    coll = new ArrayList<Object>();
                else if (Set.class.isAssignableFrom(type))
                    coll = new LinkedHashSet<Object>();
                else if (Queue.class.isAssignableFrom(type))
                    coll = new LinkedBlockingDeque<Object>();
            }
            if (coll != null) {
                JsonArray jsonArray = (JsonArray) jsonVal;
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
                    @SuppressWarnings("unchecked")
                    Map<String, Object> instance = (Map<String, Object>) ctor0.newInstance();
                    map = instance;
                } catch (NoSuchMethodException e) {
                    map = new HashMap<String, Object>();
                }
                if (map != null) {
                    JsonObject jsonMap = (JsonObject) jsonVal;
                    for (String key : jsonMap.keySet()) {
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

}
