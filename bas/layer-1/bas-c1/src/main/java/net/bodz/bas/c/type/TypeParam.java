package net.bodz.bas.c.type;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.UnexpectedException;

public class TypeParam {

    public static Type[] getImmediatePV(Class<?> clazz) {
        Type superclass = clazz.getGenericSuperclass();
        if (superclass instanceof ParameterizedType) {
            ParameterizedType psuper = (ParameterizedType) superclass;
            return psuper.getActualTypeArguments();
        } else
            return null;
    }

    public static Type[] getOriginPV(Type type) {
        if (type == null)
            throw new NullPointerException("type");

        if (type instanceof ParameterizedType) {
            ParameterizedType ptype = (ParameterizedType) type;
            return ptype.getActualTypeArguments();
        }

        if (type instanceof Class<?>) {
            Class<?> clazz = (Class<?>) type;
            Type superclass = clazz.getGenericSuperclass();
            if (superclass == null)
                return null;
            return getOriginPV(superclass);
        }

        throw new IllegalUsageException("Not a generic type");
    }

    public static Class<?>[] getOriginPVClass(Type type) {
        Type[] pv = getOriginPV(type);
        if (pv == null)
            return null;

        Class<?>[] pvClasses = new Class<?>[pv.length];
        for (int i = 0; i < pv.length; i++)
            pvClasses[i] = bound1(pv[i]);

        return pvClasses;
    }

    /**
     * Recursive get the first concrete type bound of the type (or type variable).
     * 
     * @return Non-<code>null</code> first concrete type bound.
     * @throws Error
     *             If failed to get the type bound.
     */
    public static <T> Class<T> bound1(Type type) {
        while (type instanceof TypeVariable<?>) {
            Type[] bounds = ((TypeVariable<?>) type).getBounds();

            // super or extends?
            Type bound1 = bounds[0];

            type = bound1;
        }

        if (type instanceof ParameterizedType)
            type = ((ParameterizedType) type).getRawType();

        if (!(type instanceof Class<?>))
            throw new Error("Failed to get type bound: " + type);

        @SuppressWarnings("unchecked") Class<T> cast = (Class<T>) type;

        return cast;
    }

    public static String traceBound(Type type) {
        if (type instanceof TypeVariable<?>) {
            TypeVariable<?> tvar = (TypeVariable<?>) type;

            StringBuilder sb = new StringBuilder();
            sb.append(tvar + "(");

            Type[] bounds = tvar.getBounds();
            for (int i = 0; i < bounds.length; i++) {
                if (i != 0)
                    sb.append(", ");
                sb.append(traceBound(bounds[i]));
            }

            sb.append(")");
            return sb.toString();
        } else
            return type.toString();
    }

    public static String[] traceBounds(Type[] types) {
        String[] bounds = new String[types.length];
        for (int i = 0; i < types.length; i++) {
            bounds[i] = traceBound(types[i]);
        }
        return bounds;
    }

    public static <T> Class<T> infer1(Class<?> clazz, Class<?> interesting, int index) {
        Type[] typeArgs = getTypeArgs(clazz, interesting);
        return bound1(typeArgs[index]);
    }

    public static <T> Class<T> infer1(Type type, Class<?> interesting, int index) {
        Type[] typeArgs = getTypeArgs(type, interesting);
        return bound1(typeArgs[index]);
    }

    public static Type[] getTypeArgs(Type type, Class<?> interesting) {
        if (type instanceof Class<?>)
            return getTypeArgs((Class<?>) type, interesting);

        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Class<?> rawType = (Class<?>) parameterizedType.getRawType();
            Type[] argv = parameterizedType.getActualTypeArguments();
            return mapTypeArgsRec(rawType, interesting, argv);
        }

        throw new UnsupportedOperationException("Unsupported type: " + type);
    }

    /**
     * This is the same as {@link #mapTypeArgsRec(Class, Class, Type[])} with <code>argv</code> set
     * to <code>clazz.getTypeParameters()</code>.
     */
    public static Type[] getTypeArgs(Class<?> clazz, Class<?> interesting) {
        if (clazz == null)
            throw new NullPointerException("clazz");
        if (interesting == null)
            throw new NullPointerException("interesting");
        if (!interesting.isAssignableFrom(clazz))
            throw new IllegalUsageException(clazz + " is-not-a " + interesting);

        clazz = TypeHack.skipProxies(clazz); // OPT: why should proxy be skipped?

        TypeVariable<?>[] argv = clazz.getTypeParameters();

        return mapTypeArgsRec(clazz, interesting, argv);
    }

    static Type[] EMPTY = {};

    /**
     * Traverse and search interested base class (<code>interesting</code>) with-in the start class
     * (<code>clazz</code>), and map the type parameters (<code>argv</code>) to be the parameters
     * for the interesting base.
     * 
     * <p>
     * If interesting base is not generic, an empty array is immediately returned.
     * 
     * <p>
     * If the interesting isn't an interface, no generic interface is looked into. This maybe
     * slightly more efficient then query interesting interface.
     * 
     * @param clazz
     *            Start class to traverse.
     * @param interesting
     *            The interesting class or interface. An empty array is immediately returned if
     *            interesting base isn't generic.
     * @param argv
     *            The type parameters which would be suitable for the interesting base.
     * @return Non-<code>null</code> actual parameters for the interesting base.
     * @throws IllegalUsageException
     *             If interesting base doesn't found with-in the start class.
     */
    static Type[] mapTypeArgsRec(Class<?> clazz, Class<?> interesting, Type[] argv) {
        if (clazz == interesting)
            return argv;

        if (interesting.getTypeParameters().length == 0)
            return EMPTY;

        TypeVariable<?>[] declVars = clazz.getTypeParameters();

        if (interesting.isInterface())
            for (Type iface : clazz.getGenericInterfaces()) {
                Class<?> ifaceRaw;
                ParameterizedType iface__;

                if (iface instanceof ParameterizedType) {
                    iface__ = (ParameterizedType) iface;
                    ifaceRaw = (Class<?>) iface__.getRawType();
                } else {
                    iface__ = null;
                    ifaceRaw = (Class<?>) iface;
                }

                if (!interesting.isAssignableFrom(ifaceRaw))
                    continue;

                Type[] actualv = EMPTY;
                if (iface__ != null)
                    actualv = iface__.getActualTypeArguments();

                Type[] mapped = reorder(declVars, actualv, argv);

                return mapTypeArgsRec(ifaceRaw, interesting, mapped);
            }

        Class<?> superclassRaw = clazz.getSuperclass();
        if (superclassRaw == null)
            throw new IllegalUsageException("No " + interesting + " matched in base classes");

        Type superclass = clazz.getGenericSuperclass();
        Type[] superArgs;

        if (superclass instanceof ParameterizedType) {
            ParameterizedType superclass__ = (ParameterizedType) superclass;
            Type[] actualv = superclass__.getActualTypeArguments();

            superArgs = reorder(declVars, actualv, argv);
        } else {
            superArgs = EMPTY;
        }

        return mapTypeArgsRec(clazz.getSuperclass(), interesting, superArgs);
    }

    static Type[] reorder(TypeVariable<?>[] declVars, Type[] actualv, Type[] argv) {
        Type[] mapped = new Type[actualv.length];
        V: for (int i = 0; i < actualv.length; i++) {
            Type actual = actualv[i];
            if (actual instanceof TypeVariable<?>) {

                // declare-var[j] => actual-var[i], then args[j]
                TypeVariable<?> actualVar = (TypeVariable<?>) actual;
                String actualName = actualVar.getName();
                for (int j = 0; j < declVars.length; j++) {
                    if (declVars[j].getName().equals(actualName)) {
                        try {
                            mapped[i] = argv[j];
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println(1);
                        }
                        continue V;
                    }
                }
                throw new UnexpectedException("No type parameter with name " + actualName + " is declared.");
            }
            mapped[i] = actual;
        }
        return mapped;
    }

}