package net.bodz.swt.gui;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyChangeListener;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.bodz.bas.lang.a.ReadOnly;
import net.bodz.bas.lang.err.CheckException;
import net.bodz.bas.lang.err.CreateException;
import net.bodz.bas.lang.err.IllegalUsageError;
import net.bodz.bas.lang.err.UnexpectedException;
import net.bodz.bas.lang.util.MethodParameter;
import net.bodz.bas.log.LogOut;
import net.bodz.bas.log.LogOuts;
import net.bodz.bas.types.Checker;
import net.bodz.bas.types.Checks;
import net.bodz.swt.gui.GUIVars.GUIFieldMeta;
import net.bodz.swt.gui.GUIVars.GUIFieldVar;
import net.bodz.swt.gui.GUIVars.GUIPropertyMeta;
import net.bodz.swt.gui.GUIVars.GUIPropertyVar;

public class GUIStructs {

    public static class ClassMeta {

        public static final int         FIELDS       = 1;
        public static final int         PROPERTIES   = 2;
        public static final int         METHODS      = 4;

        /** include those private/protected members */
        public static final int         ALL_DECL     = 32;

        /** setAccessible on the members */
        public static final int         FORCE_ACCESS = 64;

        /** Properties with same name will overwrite the fields */
        public static final int         NO_DUP_PROPS = 0x100;

        private static final int        DEFAULT;
        static {
            DEFAULT = FIELDS | PROPERTIES | METHODS | ALL_DECL | NO_DUP_PROPS;
        }

        private final ClassMeta         prev;
        private GUIHint                 hint;

        private List<GUIVarMeta>        list;
        private Map<String, GUIVarMeta> map;

        private static LogOut           out          = LogOuts.debug;

        protected ClassMeta(ClassMeta prev, Class<?> clazz)
                throws GUIAccessException {
            this(prev, clazz, DEFAULT);
        }

        protected ClassMeta(ClassMeta prev, Class<?> clazz, int flags)
                throws GUIAccessException {
            this.prev = prev;
            this.hint = new GUIHint(//
                    prev == null ? null : prev.hint, clazz);
            boolean nodup = (flags & NO_DUP_PROPS) != 0;
            if (nodup)
                map = new HashMap<String, GUIVarMeta>();
            else
                list = new ArrayList<GUIVarMeta>();

            boolean all = (flags & ALL_DECL) != 0;
            boolean force = (flags & FORCE_ACCESS) != 0;
            if ((flags & FIELDS) != 0)
                for (Field field : //
                all ? clazz.getDeclaredFields() : clazz.getFields()) {
                    if (force)
                        field.setAccessible(true);
                    else if (!canAccess(field))
                        continue;
                    add(field);
                }
            if ((flags & METHODS) != 0)
                for (Method method : //
                all ? clazz.getMethods() : clazz.getMethods()) {
                    if (force)
                        method.setAccessible(true);
                    else if (!canAccess(method))
                        continue;
                    add(method);
                }
            if ((flags & PROPERTIES) != 0)
                addProperties(clazz);
        }

        <T extends AccessibleObject & Member> boolean canAccess(T member) {
            if (member.isAccessible())
                return true;
            if (Modifier.isPublic(member.getModifiers()))
                return true;
            return false;
        }

        public static ClassMeta get(Class<?> clazz) throws GUIAccessException {
            Class<?> p = clazz.getSuperclass();
            if (p == Object.class || p == null)
                return new ClassMeta(null, clazz);
            else
                return new ClassMeta(get(p), clazz);
        }

        public static ClassMeta get(Class<?> clazz, int flags)
                throws GUIAccessException {
            Class<?> p = clazz.getSuperclass();
            if (p == Object.class || p == null)
                return new ClassMeta(null, clazz, flags);
            else
                return new ClassMeta(get(p), clazz, flags);
        }

        void add(String name, GUIVarMeta meta) {
            if (map != null) {
                if (map.containsKey(name))
                    out.P("NO-DUP: item ", name, " in is overwritten: ", meta);
                map.put(name, meta);
            } else
                list.add(meta);
        }

        public void add(Field field) throws GUIAccessException {
            String name = field.getName();
            GUIFieldMeta meta = new GUIFieldMeta(field);
            add(name, meta);
        }

        public void add(Method method) throws GUIAccessException {
            String name = method.getName();
            GUIMethodMeta meta = new GUIMethodMeta(method);
            add(name, meta);
        }

        void addProperties(Class<?> clazz) throws GUIAccessException {
            BeanInfo bean;
            try {
                bean = Introspector.getBeanInfo(clazz, Object.class);
            } catch (IntrospectionException e) {
                throw new GUIAccessException(e);
            }
            for (PropertyDescriptor property : bean.getPropertyDescriptors()) {
                if (property.getReadMethod() == null)
                    // skip write-only properties.
                    continue;
                add(property);
            }
        }

        public void add(PropertyDescriptor property) {
            String name = property.getName();
            GUIPropertyMeta meta = new GUIPropertyMeta(property);
            add(name, meta);
        }

        protected void getContents(List<GUIVarMeta> buf) {
            if (map != null)
                buf.addAll(map.values());
            else
                buf.addAll(list);
            if (prev != null)
                prev.getContents(buf);
        }

        public List<GUIVarMeta> getContents() {
            List<GUIVarMeta> buf = new ArrayList<GUIVarMeta>( //
                    map != null ? map.size() : list.size());
            getContents(buf);
            return buf;
        }

    }

    public static class GUIObjectStruct extends _GUIStruct {

        private static final long serialVersionUID = 1035728161115334065L;

        private final ClassMeta   meta;
        private Object            object;

        public GUIObjectStruct(Object object) {
            Class<?> clazz = object.getClass();
            this.meta = ClassMeta.get(clazz);
            this.object = object;
            List<GUIVarMeta> metas = this.meta.getContents();
            // sort?
            for (GUIVarMeta meta : metas) {
                GUIVar<Object> var;
                if (meta instanceof GUIFieldMeta) {
                    GUIFieldMeta fieldMeta = (GUIFieldMeta) meta;
                    var = new GUIFieldVar<Object>(fieldMeta, object);
                } else if (meta instanceof GUIPropertyMeta) {
                    GUIPropertyMeta propertyMeta = (GUIPropertyMeta) meta;
                    var = new GUIPropertyVar<Object>(propertyMeta, object);
                } else if (meta instanceof GUIMethodMeta) {
                    // GUIMethodMeta methodMeta = (GUIMethodMeta) meta;
                    // var = new GUIMethodCall(methodMeta, object);
                    continue;
                } else {
                    throw new UnexpectedException("unknown member meta type: "
                            + meta);
                }
                add(var);
            }
        }

        @Override
        public String toString() {
            return meta + "@" + object;
        }

    }

    public static class GUIMethodMeta implements GUIVarMeta {

        protected Method          method;
        protected ParameterMeta[] parameters;
        protected GUIHint         hint;

        public GUIMethodMeta(Method method) {
            this.method = method;
            MethodParameter[] mpv = MethodParameter.getParameters(method);
            parameters = new ParameterMeta[mpv.length];
            for (int i = 0; i < parameters.length; i++)
                parameters[i] = new ParameterMeta(mpv[i]);
            this.hint = GUIHint.get(method);
        }

        @Override
        public String getName() {
            return method.getName();
        }

        @Override
        public Class<?> getType() {
            /**
             * TODO - Method Type?
             */
            return null;
        }

        @Override
        public int getModifiers() {
            return method.getModifiers();
        }

        @Override
        public boolean isReadOnly() {
            return true;
        }

        @Override
        public boolean hasPropertyChangeSupport() {
            return false;
        }

        @Override
        public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
            return method.getAnnotation(annotationClass);
        }

        @Override
        public Annotation[] getAnnotations() {
            return method.getAnnotations();
        }

        @Override
        public Annotation[] getDeclaredAnnotations() {
            return method.getDeclaredAnnotations();
        }

        @Override
        public boolean isAnnotationPresent(
                Class<? extends Annotation> annotationClass) {
            return method.isAnnotationPresent(annotationClass);
        }

        @Override
        public GUIHint getHint() {
            return hint;
        }

        public GUIMethodCall prepareCall(Object object, Object... initArgs) {
            GUIMethodCall call = new GUIMethodCall(this, initArgs);
            return call;
        }

        @Override
        public String toString() {
            return method.toString();
        }

    }

    public static class GUIMethodCall extends _GUIStruct {

        private static final long serialVersionUID = 7447549523460668389L;

        protected GUIMethodMeta   meta;

        /**
         * insufficient arguments default to null. extra arguments are ignored.
         */
        public GUIMethodCall(GUIMethodMeta meta, Object... initArgs) {
            this.meta = meta;
            int n = meta.parameters.length;
            for (int i = 0; i < n; i++) {
                ParameterMeta paramMeta = meta.parameters[i];
                ParameterVar<Object> paramVar = new ParameterVar<Object>(
                        paramMeta);
                if (i < initArgs.length) {
                    try {
                        paramVar.check(initArgs[i]);
                    } catch (CheckException e) {
                        throw new IllegalUsageError(
                                "Check failed on init arguments");
                    }
                    paramVar.set(initArgs[i]);
                }
                add(paramVar);
            }
        }

    }

    public static class ParameterMeta implements GUIVarMeta {

        protected final MethodParameter param;
        protected final int             index;
        protected final boolean         readOnly;
        protected final GUIHint         hint;
        protected final Checker         checker;

        public ParameterMeta(MethodParameter param) {
            this.param = param;
            index = param.getIndex();
            readOnly = param.isAnnotationPresent(ReadOnly.class);
            hint = GUIHint.get(param);
            try {
                checker = Checks.getChecker(param);
            } catch (CreateException e) {
                throw new RuntimeException(e);
            }
        }

        static final int MODIFIERS = Modifier.PUBLIC | Modifier.TRANSIENT;

        @Override
        public String getName() {
            return param.getName();
        }

        @Override
        public Class<?> getType() {
            return param.getType();
        }

        @Override
        public int getModifiers() {
            return MODIFIERS;
        }

        @Override
        public boolean isReadOnly() {
            return readOnly;
        }

        @Override
        public boolean hasPropertyChangeSupport() {
            return false;
        }

        @Override
        public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
            return param.getAnnotation(annotationClass);
        }

        @Override
        public Annotation[] getAnnotations() {
            return param.getAnnotations();
        }

        @Override
        public Annotation[] getDeclaredAnnotations() {
            return param.getDeclaredAnnotations();
        }

        @Override
        public boolean isAnnotationPresent(
                Class<? extends Annotation> annotationClass) {
            return param.isAnnotationPresent(annotationClass);
        }

        @Override
        public GUIHint getHint() {
            return hint;
        }

        public void check(Object value) throws CheckException {
            Class<?> type = getType();
            if (value != null && !type.isInstance(value))
                throw new CheckException("Not a instanceof " + type + ": "
                        + value);
            if (checker != null)
                checker.check(value);
        }

        // public Object get(Object obj) throws GUIAccessException {
        // if (!(obj instanceof Object[]))
        // throw new IllegalArgumentException("not an param array: " + obj);
        // Object[] args = (Object[]) obj;
        // return args[index];
        // }
        //
        // public void set(Object obj, Object value) throws GUIAccessException {
        // if (!(obj instanceof Object[]))
        // throw new IllegalArgumentException("not an param array: " + obj);
        // Object[] args = (Object[]) obj;
        // args[index] = value;
        // }

        @Override
        public String toString() {
            return param + "[" + index + "]";
        }

    }

    public static class ParameterVar<T> implements GUIVar<T> {

        private final ParameterMeta meta;
        private T                   value;

        public ParameterVar(ParameterMeta meta) {
            assert meta != null;
            this.meta = meta;
        }

        @Override
        public GUIVarMeta getMeta() {
            return meta;
        }

        @Override
        public T get() {
            return value;
        }

        @Override
        public void set(T value) {
            this.value = value;
        }

        @Override
        public void addPropertyChangeListener(PropertyChangeListener listener) {
        }

        @Override
        public void removePropertyChangeListener(PropertyChangeListener listener) {
        }

        @Override
        public void check(T newValue) throws CheckException {
            meta.check(newValue);
        }

    }

}
