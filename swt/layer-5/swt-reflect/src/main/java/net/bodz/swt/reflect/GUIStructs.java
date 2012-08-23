package net.bodz.swt.reflect;

import static net.bodz.swt.nls.GUINLS.GUINLS;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyChangeListener;
import java.beans.PropertyDescriptor;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.c.reflect.MethodSignature;
import net.bodz.bas.err.CheckException;
import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.IllegalUsageError;
import net.bodz.bas.err.ReadOnlyException;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerColo;
import net.bodz.bas.trait.Traits;
import net.bodz.bas.traits.IValidator;
import net.bodz.bas.traits.ValidateException;
import net.bodz.bas.util.primitive.Primitives;
import net.bodz.swt.gui.err.GUIAccessException;
import net.bodz.swt.reflect.GUIVars.GUIFieldMeta;
import net.bodz.swt.reflect.GUIVars.GUIFieldVar;
import net.bodz.swt.reflect.GUIVars.GUIPropertyMeta;
import net.bodz.swt.reflect.GUIVars.GUIPropertyVar;

public class GUIStructs {

    static Logger logger = LoggerColo.getInstance().get(1);

    public static class ClassMeta {

        public static final int FIELDS = 1;
        public static final int PROPERTIES = 2;
        public static final int METHODS = 4;

        /** include those private/protected members */
        public static final int ALL_DECL = 32;

        /** setAccessible on the members */
        public static final int FORCE_ACCESS = 64;

        /** Properties with same name will overwrite the fields */
        public static final int NO_DUP_PROPS = 0x100;

        private static final int DEFAULT;
        static {
            DEFAULT = FIELDS | PROPERTIES | METHODS | ALL_DECL | FORCE_ACCESS | NO_DUP_PROPS;
        }

        private final ClassMeta prev;
        private SwtEntryMetadata hint;

        private List<SwtEntryMetadata1> list;
        private Map<String, SwtEntryMetadata1> map;

        protected ClassMeta(ClassMeta prev, Class<?> clazz)
                throws GUIAccessException {
            this(prev, clazz, DEFAULT);
        }

        protected ClassMeta(ClassMeta prev, Class<?> clazz, int flags)
                throws GUIAccessException {
            this.prev = prev;
            this.hint = new SwtEntryMetadata(//
                    prev == null ? null : prev.hint, clazz);
            boolean nodup = (flags & NO_DUP_PROPS) != 0;
            if (nodup)
                map = new HashMap<String, SwtEntryMetadata1>();
            else
                list = new ArrayList<SwtEntryMetadata1>();

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
                all ? clazz.getDeclaredMethods() : clazz.getMethods()) {
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

        public static ClassMeta get(Class<?> clazz)
                throws GUIAccessException {
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

        void add(String name, SwtEntryMetadata1 meta) {
            if (map != null) {
                if (map.containsKey(name))
                    logger.warnf(GUINLS.getString("GUIStructs.dupItem_sss"), //
                            name, this, meta.getName());
                map.put(name, meta);
            } else
                list.add(meta);
        }

        public void add(Field field)
                throws GUIAccessException {
            String name = field.getName();
            GUIFieldMeta meta = new GUIFieldMeta(field);
            add(name, meta);
        }

        static final Set<MethodSignature> ignoreMethods;
        static {
            ignoreMethods = new HashSet<MethodSignature>();
            ignoreMethods.add(new MethodSignature("toString"));
            ignoreMethods.add(new MethodSignature("hashcode"));
            ignoreMethods.add(new MethodSignature("equals", Object.class));
            ignoreMethods.add(new MethodSignature("clone"));
            ignoreMethods.add(new MethodSignature("readObject", ObjectInputStream.class));
            ignoreMethods.add(new MethodSignature("writeObject", ObjectOutputStream.class));
            ignoreMethods.add(new MethodSignature("readObjectNoDate"));
        }

        public void add(Method method)
                throws GUIAccessException {
            MethodSignature sig = new MethodSignature(method);
            if (ignoreMethods.contains(sig))
                return;
            String name = method.getName();
            GUICallMeta meta = new GUICallMeta(method);
            add(name, meta);
        }

        void addProperties(Class<?> clazz)
                throws GUIAccessException {
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

        protected void getContents(List<SwtEntryMetadata1> buf) {
            if (map != null)
                buf.addAll(map.values());
            else
                buf.addAll(list);
            if (prev != null)
                prev.getContents(buf);
        }

        public List<SwtEntryMetadata1> getContents() {
            List<SwtEntryMetadata1> buf = new ArrayList<SwtEntryMetadata1>( //
                    map != null ? map.size() : list.size());
            getContents(buf);
            return buf;
        }

    }

    public static class GUIObjectStruct
            extends _GUIStruct {

        private static final long serialVersionUID = 1035728161115334065L;

        private final ClassMeta meta;
        private Object object;

        public GUIObjectStruct(Object object) {
            Class<?> clazz = object.getClass();
            this.meta = ClassMeta.get(clazz);
            this.object = object;
            List<SwtEntryMetadata1> metas = this.meta.getContents();
            // sort?
            for (SwtEntryMetadata1 meta : metas) {
                SwtEntry<?> var;
                if (meta instanceof GUIFieldMeta) {
                    GUIFieldMeta fieldMeta = (GUIFieldMeta) meta;
                    var = new GUIFieldVar<Object>(fieldMeta, object);
                } else if (meta instanceof GUIPropertyMeta) {
                    GUIPropertyMeta propertyMeta = (GUIPropertyMeta) meta;
                    var = new GUIPropertyVar<Object>(propertyMeta, object);
                } else if (meta instanceof GUICallMeta) {
                    GUICallMeta methodMeta = (GUICallMeta) meta;
                    GUICallVar callVar = methodMeta.newCall(object);
                    var = callVar;
                } else {
                    throw new UnexpectedException(GUINLS.getString("GUIStructs.unknownMemberMetaType") + meta);
                }
                add(var);
            }
        }

        @Override
        public String toString() {
            return meta + "@" + object;
        }

    }

    public static class ParametersMeta {

        protected ParameterMeta[] mv;

        public ParametersMeta(MethodParameter[] mpv) {
            mv = new ParameterMeta[mpv.length];
            for (int i = 0; i < mv.length; i++)
                mv[i] = new ParameterMeta(mpv[i]);
        }

        public int size() {
            return mv.length;
        }

        public ParameterMeta get(int index) {
            return mv[index];
        }

        public Class<?> getType(int index) {
            return mv[index].getType();
        }

    }

    public static class ParametersStruct
            extends _GUIStruct {

        private static final long serialVersionUID = 3563070864386890361L;

        private CallContext cc;

        public ParametersStruct(ParametersMeta meta, CallContext cc, Object... initArgs) {
            super(meta.size());
            this.cc = cc;
            int n = meta.size();
            for (int i = 0; i < n; i++) {
                ParameterMeta paramMeta = meta.get(i);
                ParameterVar paramVar = new ParameterVar(paramMeta, cc);
                if (i < initArgs.length) {
                    try {
                        paramVar.validate(initArgs[i]);
                    } catch (CheckException e) {
                        throw new IllegalUsageError(GUINLS.getString("GUIStructs.checkFailOnInit"));
                    }
                    paramVar.set(initArgs[i]);
                }
                add(paramVar);
            }
        }

        public CallContext getCallContext() {
            return cc;
        }

    }

    public static class GUICallMeta
            implements SwtEntryMetadata1 {

        protected Method method;
        protected SwtEntryMetadata hint;
        protected RetvalMeta retvalMeta;
        protected ParametersMeta parametersMeta;

        public GUICallMeta(Method method) {
            this.method = method;
            this.hint = SwtEntryMetadata.get(method);
            this.retvalMeta = new RetvalMeta(method);
            MethodParameter[] mpv = MethodParameter.getParameters(method);
            this.parametersMeta = new ParametersMeta(mpv);
        }

        @Override
        public String getName() {
            return method.getName();
        }

        @Override
        public Class<?> getType() {
            return CallObject.class;
        }

        public Class<?> getReturnType() {
            return retvalMeta.getType();
        }

        public Class<?> getParameterType(int index) {
            return parametersMeta.getType(index);
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
        public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
            return method.isAnnotationPresent(annotationClass);
        }

        @Override
        public SwtEntryMetadata getHint() {
            return hint;
        }

        public GUICallVar newCall(Object object, Object... initArgs) {
            GUICallVar call = new GUICallVar(this, object, initArgs);
            return call;
        }

        @Override
        public String toString() {
            return method.toString();
        }

    }

    public static class GUICallVar
            extends ParametersStruct
            implements SwtEntry<CallObject> {

        private static final long serialVersionUID = 7447549523460668389L;

        protected final GUICallMeta meta;
        protected RetvalVar retval;

        /**
         * insufficient arguments default to null. extra arguments are ignored.
         */
        public GUICallVar(GUICallMeta meta, Object object, Object... initArgs) {
            super(meta.parametersMeta, new CallObject(object, meta.method));
            this.meta = meta;
            retval = new RetvalVar(meta.retvalMeta, getCallContext());
            // add(retvalVar);
        }

        @Override
        public CallObject getCallContext() {
            return (CallObject) super.getCallContext();
        }

        @Override
        public GUICallMeta getMetadata() {
            return meta;
        }

        public RetvalVar getRetval() {
            return retval;
        }

        @Override
        public CallObject get() {
            return getCallContext();
        }

        @Override
        public void set(Object value) {
            throw new ReadOnlyException();
        }

        @Override
        public void validate(Object newValue)
                throws CheckException {
            // read-only
        }

        @Override
        public void addPropertyChangeListener(PropertyChangeListener listener) {
        }

        @Override
        public void removePropertyChangeListener(PropertyChangeListener listener) {
        }

        public Object invoke()
                throws ReflectiveOperationException {
            return getCallContext().invoke();
        }

    }

    public static class RetvalMeta
            implements SwtEntryMetadata1 {

        protected final Method method;
        protected final SwtEntryMetadata hint;

        public RetvalMeta(Method method) {
            this.method = method;
            this.hint = null; // no annotation on the retval.
        }

        static final int MODIFIERS = Modifier.PUBLIC | Modifier.TRANSIENT;

        @Override
        public String getName() {
            return GUINLS.getString("GUIStructs._retval");
        }

        @Override
        public Class<?> getType() {
            return method.getReturnType();
        }

        @Override
        public int getModifiers() {
            return MODIFIERS;
        }

        @Override
        public boolean isReadOnly() {
            return true;
        }

        @Override
        public boolean hasPropertyChangeSupport() {
            return true;
        }

        @Override
        public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
            return null;
        }

        private static final Annotation[] empty = {};

        @Override
        public Annotation[] getAnnotations() {
            return empty;
        }

        @Override
        public Annotation[] getDeclaredAnnotations() {
            return empty;
        }

        @Override
        public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
            return false;
        }

        @Override
        public SwtEntryMetadata getHint() {
            return hint;
        }

        public void check(Object value)
                throws CheckException {
            // read-only
        }

        @Override
        public String toString() {
            return GUINLS.getString("GUIStructs.retval");
        }

    }

    public static class RetvalVar
            implements SwtEntry<Object> {

        private final RetvalMeta meta;
        private final CallContext cc;

        public RetvalVar(RetvalMeta meta, CallContext cc) {
            assert meta != null;
            this.meta = meta;
            this.cc = cc;
        }

        @Override
        public SwtEntryMetadata1 getMetadata() {
            return meta;
        }

        @Override
        public Object get() {
            return cc.getRetval();
        }

        @Override
        public void set(Object value) {
            cc.setRetval(value);
        }

        @Override
        public void validate(Object newValue)
                throws CheckException {
            meta.check(newValue);
        }

        @Override
        public void addPropertyChangeListener(PropertyChangeListener listener) {
            cc.addPropertyChangeListener(listener);
        }

        @Override
        public void removePropertyChangeListener(PropertyChangeListener listener) {
            cc.removePropertyChangeListener(listener);
        }

    }

    public static class ParameterMeta
            implements SwtEntryMetadata1 {

        protected final MethodParameter param;
        protected final int index;
        protected final boolean readOnly;
        protected final SwtEntryMetadata hint;
        protected final IValidator<Object> checker;

        public ParameterMeta(MethodParameter param) {
            this.param = param;
            index = param.getIndex();
            readOnly = param.isAnnotationPresent(ReadOnly.class);
            hint = SwtEntryMetadata.get(param);
            try {
                checker = Traits.getTrait(param, IValidator.class);
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
            return true;
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
        public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
            return param.isAnnotationPresent(annotationClass);
        }

        @Override
        public SwtEntryMetadata getHint() {
            return hint;
        }

        public void validate(Object value)
                throws ValidateException {
            Class<?> type = getType();
            if (value != null && !Primitives.box(type).isInstance(value))
                throw new CheckException(GUINLS.getString("GUIStructs.notInstOf") + type + ": " + value);
            if (checker != null)
                checker.validate(value);
        }

        @Override
        public String toString() {
            return param + "[" + index + "]";
        }

    }

    public static class ParameterVar
            implements SwtEntry<Object> {

        private final ParameterMeta meta;
        private final CallContext cc;

        public ParameterVar(ParameterMeta meta, CallContext cc) {
            assert meta != null;
            this.meta = meta;
            this.cc = cc;
        }

        @Override
        public SwtEntryMetadata1 getMetadata() {
            return meta;
        }

        @Override
        public Object get() {
            return cc.get(meta.index);
        }

        @Override
        public void set(Object value) {
            cc.set(meta.index, value);
        }

        @Override
        public void validate(Object newValue)
                throws ValidateException {
            meta.validate(newValue);
        }

        @Override
        public void addPropertyChangeListener(PropertyChangeListener listener) {
            cc.addPropertyChangeListener(listener);
        }

        @Override
        public void removePropertyChangeListener(PropertyChangeListener listener) {
            cc.removePropertyChangeListener(listener);
        }

    }

}
