package net.bodz.bas.json;

import java.io.Closeable;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.c.java.util.SortMode;
import net.bodz.fork.org.json.JSONException;
import net.bodz.fork.org.json.JSONPropertyIgnore;
import net.bodz.fork.org.json.JSONPropertyName;
import net.bodz.fork.org.json.JSONTokener;
import net.bodz.fork.org.json._JSONObject;

import java.util.ResourceBundle;

public class JsonObjectBuilder {

    SortMode sortMode = SortMode.UNSET;

    public SortMode getSortMode() {
        return sortMode;
    }

    public void setSortMode(SortMode sortMode) {
        this.sortMode = sortMode;
    }

    protected Map<String, Object> createMap() {
        return sortMode.newMap();
    }

    /**
     * Construct an empty _JSONObject.
     */
    public JsonObject create() {
        // HashMap is used on purpose to ensure that elements are unordered by
        // the specification.
        // JSON tends to be a portable transfer format to allows the container
        // implementations to rearrange their items for a faster element
        // retrieval based on associative access.
        // Therefore, an implementation mustn't rely on the order of the item.
        Map<String, Object> map = createMap();
        return fromMap(map);
    }

    /**
     * Construct a JsonObject from a subset of another JsonObject. An array of strings is used to
     * identify the keys that should be copied. Missing keys are ignored.
     *
     * @param jo
     *            A _JSONObject.
     * @param names
     *            An array of strings.
     */
    public JsonObject fromSubset(JsonObject jo, String... names) {
        JsonObject _this = create();
        for (int i = 0; i < names.length; i += 1) {
            try {
                _this.putOnce(names[i], jo.get(names[i]));
            } catch (Exception ignore) {
            }
        }
        return _this;
    }

    /**
     * Construct a _JSONObject from a Map.
     *
     * @param m
     *            A map object that can be used to initialize the contents of the _JSONObject.
     * @throws JSONException
     *             If a value in the map is non-finite number.
     * @throws NullPointerException
     *             If a key in the map is <code>null</code>
     */
    public JsonObject fromMap(Map<?, ?> m) {
        Map<String, Object> map = createMap();
        for (final Entry<?, ?> e : m.entrySet()) {
            if (e.getKey() == null) {
                throw new NullPointerException("Null key.");
            }
            final Object value = e.getValue();
            if (value != null) {
                map.put(String.valueOf(e.getKey()), JsonObject.wrap(value));
            }
        }
        return new JsonObject(map);
    }

    /**
     * Construct a _JSONObject from a source JSON text string. This is the most commonly used
     * _JSONObject constructor.
     *
     * @param source
     *            A string beginning with <code>{</code>&nbsp;<small>(left brace)</small> and ending
     *            with <code>}</code> &nbsp;<small>(right brace)</small>.
     * @exception JSONException
     *                If there is a syntax error in the source string or a duplicated key.
     */
    public JsonObject parse(String source)
            throws JSONException {
        return parse(new JSONTokener(source));
    }

    /**
     * Construct a _JSONObject from a JSONTokener.
     *
     * @param x
     *            A JSONTokener object containing the source string.
     * @throws JSONException
     *             If there is a syntax error in the source string or a duplicated key.
     */
    public JsonObject parse(JSONTokener x)
            throws JSONException {
        JsonObject _this = create();

        char c;
        String key;

        if (x.nextClean() != '{') {
            throw x.syntaxError("A _JSONObject text must begin with '{'");
        }
        for (;;) {
            c = x.nextClean();
            switch (c) {
            case 0:
                throw x.syntaxError("A _JSONObject text must end with '}'");
            case '}':
                return _this;
            default:
                x.back();
                key = x.nextValue().toString();
            }

            // The key is followed by ':'.

            c = x.nextClean();
            if (c != ':') {
                throw x.syntaxError("Expected a ':' after a key");
            }

            // Use syntaxError(..) to include error location

            if (key != null) {
                // Check if key exists
                if (_this.get(key) != null) {
                    // key already exists
                    throw x.syntaxError("Duplicate key \"" + key + "\"");
                }
                // Only add value if non-null
                Object value = x.nextValue();
                if (value != null) {
                    _this.put(key, value);
                }
            }

            // Pairs are separated by ','.

            switch (x.nextClean()) {
            case ';':
            case ',':
                if (x.nextClean() == '}') {
                    return _this;
                }
                x.back();
                break;
            case '}':
                return _this;
            default:
                throw x.syntaxError("Expected a ',' or '}'");
            }
        }
    }

    /**
     * Construct a _JSONObject from a ResourceBundle.
     *
     * @param baseName
     *            The ResourceBundle base name.
     * @param locale
     *            The Locale to load the ResourceBundle for.
     * @throws JSONException
     *             If any JSONExceptions are detected.
     */
    public JsonObject fromResourceBundle(String baseName, Locale locale)
            throws JSONException {
        JsonObject root = new JsonObject();
        ResourceBundle bundle = ResourceBundle.getBundle(baseName, locale,
                Thread.currentThread().getContextClassLoader());

        // Iterate through the keys in the bundle.

        Enumeration<String> keys = bundle.getKeys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            if (key != null) {

                // Go through the path, ensuring that there is a nested _JSONObject for each
                // segment except the last. Add the value using the last segment's name into
                // the deepest nested _JSONObject.

                String[] path = ((String) key).split("\\.");
                int last = path.length - 1;
                _JSONObject target = root;
                for (int i = 0; i < last; i += 1) {
                    String segment = path[i];
                    _JSONObject nextTarget = target.getJsonObject(segment);
                    if (nextTarget == null) {
                        nextTarget = new JsonObject();
                        target.put(segment, nextTarget);
                    }
                    target = nextTarget;
                }
                target.put(path[last], bundle.getString((String) key));
            }
        }
        return root;
    }

    /**
     * Construct a _JSONObject from an Object, using reflection to find the public members. The
     * resulting _JSONObject's keys will be the strings from the names array, and the values will be
     * the field values associated with those keys in the object. If a key is not found or not
     * visible, then it will not be copied into the new _JSONObject.
     *
     * @param object
     *            An object that has fields that should be used to make a _JSONObject.
     * @param names
     *            An array of strings, the names of the fields to be obtained from the object.
     */
    public JsonObject fromReflection(Object object, String... names) {
        JsonObject _this = create();
        Class<?> c = object.getClass();
        for (int i = 0; i < names.length; i += 1) {
            String name = names[i];
            try {
                _this.putOpt(name, c.getField(name).get(object));
            } catch (Exception ignore) {
            }
        }
        return _this;
    }

    /**
     * Construct a _JSONObject from an Object using bean getters. It reflects on all of the public
     * methods of the object. For each of the methods with no parameters and a name starting with
     * <code>"get"</code> or <code>"is"</code> followed by an uppercase letter, the method is
     * invoked, and a key and the value returned from the getter method are put into the new
     * _JSONObject.
     * <p>
     * The key is formed by removing the <code>"get"</code> or <code>"is"</code> prefix. If the
     * second remaining character is not upper case, then the first character is converted to lower
     * case.
     * <p>
     * Methods that are <code>static</code>, return <code>void</code>, have parameters, or are
     * "bridge" methods, are ignored.
     * <p>
     * For example, if an object has a method named <code>"getName"</code>, and if the result of
     * calling <code>object.getName()</code> is <code>"Larry Fine"</code>, then the _JSONObject will
     * contain <code>"name": "Larry Fine"</code>.
     * <p>
     * The {@link JSONPropertyName} annotation can be used on a bean getter to override key name
     * used in the _JSONObject. For example, using the object above with the <code>getName</code>
     * method, if we annotated it with:
     *
     * <pre>
     * &#64;JSONPropertyName("FullName")
     * public String getName() {
     *     return _this.name;
     * }
     * </pre>
     *
     * The resulting JSON object would contain <code>"FullName": "Larry Fine"</code>
     * <p>
     * Similarly, the {@link JSONPropertyName} annotation can be used on non- <code>get</code> and
     * <code>is</code> methods. We can also override key name used in the _JSONObject as seen below
     * even though the field would normally be ignored:
     *
     * <pre>
     * &#64;JSONPropertyName("FullName")
     * public String fullName() {
     *     return _this.name;
     * }
     * </pre>
     *
     * The resulting JSON object would contain <code>"FullName": "Larry Fine"</code>
     * <p>
     * The {@link JSONPropertyIgnore} annotation can be used to force the bean property to not be
     * serialized into JSON. If both {@link JSONPropertyIgnore} and {@link JSONPropertyName} are
     * defined on the same method, a depth comparison is performed and the one closest to the
     * concrete class being serialized is used. If both annotations are at the same level, then the
     * {@link JSONPropertyIgnore} annotation takes precedent and the field is not serialized. For
     * example, the following declaration would prevent the <code>getName</code> method from being
     * serialized:
     *
     * <pre>
     * &#64;JSONPropertyName("FullName")
     * &#64;JSONPropertyIgnore
     * public String getName() {
     *     return _this.name;
     * }
     * </pre>
     * <p>
     *
     * @param bean
     *            An object that has getter methods that should be used to make a _JSONObject.
     */
    public JsonObject fromBean(Object bean) {
        Map<String, Object> map = createMap();
        populateMap(map, bean);
        return new JsonObject();
    }

    /**
     * Populates the internal map of the _JSONObject with the bean properties. The bean can not be
     * recursive.
     *
     * @see _JSONObject#_JSONObject(Object)
     *
     * @param bean
     *            the bean
     */
    void populateMap(Map<String, Object> map, Object bean) {
        Class<?> klass = bean.getClass();

        // If klass is a System class then set includeSuperClass to false.

        boolean includeSuperClass = klass.getClassLoader() != null;

        Method[] methods = includeSuperClass ? klass.getMethods() : klass.getDeclaredMethods();
        for (final Method method : methods) {
            final int modifiers = method.getModifiers();
            if (Modifier.isPublic(modifiers) && !Modifier.isStatic(modifiers) && method.getParameterTypes().length == 0
                    && !method.isBridge() && method.getReturnType() != Void.TYPE
                    && isValidMethodName(method.getName())) {
                final String key = getKeyNameFromMethod(method);
                if (key != null && !key.isEmpty()) {
                    try {
                        final Object result = method.invoke(bean);
                        if (result != null) {
                            map.put(key, JsonObject.wrap(result));
                            // we don't use the result anywhere outside of wrap
                            // if it's a resource we should be sure to close it
                            // after calling toString
                            if (result instanceof Closeable) {
                                try {
                                    ((Closeable) result).close();
                                } catch (IOException ignore) {
                                }
                            }
                        }
                    } catch (IllegalAccessException ignore) {
                    } catch (IllegalArgumentException ignore) {
                    } catch (InvocationTargetException ignore) {
                    }
                }
            }
        }
    }

    private static boolean isValidMethodName(String name) {
        return !"getClass".equals(name) && !"getDeclaringClass".equals(name);
    }

    private static String getKeyNameFromMethod(Method method) {
        final int ignoreDepth = getAnnotationDepth(method, JSONPropertyIgnore.class);
        if (ignoreDepth > 0) {
            final int forcedNameDepth = getAnnotationDepth(method, JSONPropertyName.class);
            if (forcedNameDepth < 0 || ignoreDepth <= forcedNameDepth) {
                // the hierarchy asked to ignore, and the nearest name override
                // was higher or non-existent
                return null;
            }
        }
        JSONPropertyName annotation = getAnnotation(method, JSONPropertyName.class);
        if (annotation != null && annotation.value() != null && !annotation.value().isEmpty()) {
            return annotation.value();
        }
        String key;
        final String name = method.getName();
        if (name.startsWith("get") && name.length() > 3) {
            key = name.substring(3);
        } else if (name.startsWith("is") && name.length() > 2) {
            key = name.substring(2);
        } else {
            return null;
        }
        // if the first letter in the key is not uppercase, then skip.
        // This is to maintain backwards compatibility before PR406
        // (https://github.com/stleary/JSON-java/pull/406/)
        if (Character.isLowerCase(key.charAt(0))) {
            return null;
        }
        if (key.length() == 1) {
            key = key.toLowerCase(Locale.ROOT);
        } else if (!Character.isUpperCase(key.charAt(1))) {
            key = key.substring(0, 1).toLowerCase(Locale.ROOT) + key.substring(1);
        }
        return key;
    }

    /**
     * Searches the class hierarchy to see if the method or it's super implementations and
     * interfaces has the annotation.
     *
     * @param <A>
     *            type of the annotation
     *
     * @param m
     *            method to check
     * @param annotationClass
     *            annotation to look for
     * @return the {@link Annotation} if the annotation exists on the current method or one of it's
     *         super class definitions
     */
    private static <A extends Annotation> A getAnnotation(final Method m, final Class<A> annotationClass) {
        // if we have invalid data the result is null
        if (m == null || annotationClass == null) {
            return null;
        }

        if (m.isAnnotationPresent(annotationClass)) {
            return m.getAnnotation(annotationClass);
        }

        // if we've already reached the Object class, return null;
        Class<?> c = m.getDeclaringClass();
        if (c.getSuperclass() == null) {
            return null;
        }

        // check directly implemented interfaces for the method being checked
        for (Class<?> i : c.getInterfaces()) {
            try {
                Method im = i.getMethod(m.getName(), m.getParameterTypes());
                return getAnnotation(im, annotationClass);
            } catch (final SecurityException ex) {
                continue;
            } catch (final NoSuchMethodException ex) {
                continue;
            }
        }

        try {
            return getAnnotation(c.getSuperclass().getMethod(m.getName(), m.getParameterTypes()), annotationClass);
        } catch (final SecurityException ex) {
            return null;
        } catch (final NoSuchMethodException ex) {
            return null;
        }
    }

    /**
     * Searches the class hierarchy to see if the method or it's super implementations and
     * interfaces has the annotation. Returns the depth of the annotation in the hierarchy.
     *
     * @param <A>
     *            type of the annotation
     *
     * @param m
     *            method to check
     * @param annotationClass
     *            annotation to look for
     * @return Depth of the annotation or -1 if the annotation is not on the method.
     */
    private static int getAnnotationDepth(final Method m, final Class<? extends Annotation> annotationClass) {
        // if we have invalid data the result is -1
        if (m == null || annotationClass == null) {
            return -1;
        }

        if (m.isAnnotationPresent(annotationClass)) {
            return 1;
        }

        // if we've already reached the Object class, return -1;
        Class<?> c = m.getDeclaringClass();
        if (c.getSuperclass() == null) {
            return -1;
        }

        // check directly implemented interfaces for the method being checked
        for (Class<?> i : c.getInterfaces()) {
            try {
                Method im = i.getMethod(m.getName(), m.getParameterTypes());
                int d = getAnnotationDepth(im, annotationClass);
                if (d > 0) {
                    // since the annotation was on the interface, add 1
                    return d + 1;
                }
            } catch (final SecurityException ex) {
                continue;
            } catch (final NoSuchMethodException ex) {
                continue;
            }
        }

        try {
            int d = getAnnotationDepth(c.getSuperclass().getMethod(m.getName(), m.getParameterTypes()),
                    annotationClass);
            if (d > 0) {
                // since the annotation was on the superclass, add 1
                return d + 1;
            }
            return -1;
        } catch (final SecurityException ex) {
            return -1;
        } catch (final NoSuchMethodException ex) {
            return -1;
        }
    }

    private JsonObjectBuilder() {
    }

    static JsonObjectBuilder instance = new JsonObjectBuilder();

    public static JsonObjectBuilder getInstance() {
        return instance;
    }

}
