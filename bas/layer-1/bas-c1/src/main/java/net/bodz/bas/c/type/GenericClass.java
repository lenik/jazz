package net.bodz.bas.c.type;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.err.UnexpectedException;

public class GenericClass {

    private final Class<?> clazz;
    private final TypeVariable<?>[] vars;
    private final Map<String, TypeVariable<?>> varMap;
    private final Type[] args;

    public GenericClass(Class<?> clazz) {
        this(clazz, null);
    }

    public GenericClass(Class<?> clazz, Type[] args) {
        this.clazz = clazz;
        this.vars = clazz.getTypeParameters();
        this.varMap = new HashMap<>();
        for (TypeVariable<?> var : vars) {
            varMap.put(var.getName(), var);
        }
        if (args != null) {
            if (args.length != vars.length)
                throw new IllegalArgumentException(String.format(//
                        "unmatched type parameter count: type %s, declared %d, actual %d", //
                        clazz.getCanonicalName(), vars.length, args.length));
        }
        this.args = args;
    }

    public static GenericClass getSuper(Class<?> clazz) {
        Type genericSuperclass = clazz.getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) genericSuperclass;
            Type[] actualTypeArguments = pt.getActualTypeArguments();
            return new GenericClass(clazz.getSuperclass(), actualTypeArguments);
        } else {
            return new GenericClass(clazz.getSuperclass(), new Type[0]);
        }
    }

    public static GenericClass[] getInterfaces(Class<?> clazz) {
        Class<?>[] interfaces = clazz.getInterfaces();
        Type[] genericInterfaces = clazz.getGenericInterfaces();
        GenericClass[] wrappers = new GenericClass[genericInterfaces.length];
        for (int i = 0; i < genericInterfaces.length; i++) {
            Type genericInterface = genericInterfaces[i];
            if (genericInterface instanceof ParameterizedType) {
                ParameterizedType pt = (ParameterizedType) genericInterface;
                Type[] actualTypeArguments = pt.getActualTypeArguments();
                wrappers[i] = new GenericClass(interfaces[i], actualTypeArguments);
            } else {
                wrappers[i] = new GenericClass(interfaces[i], new Type[0]);
            }
        }
        return wrappers;
    }

    public boolean isRaw() {
        return args == null;
    }

    public GenericClass setArgs(Type[] args) {
        return new GenericClass(clazz, args);
    }

    public Type[] eval(Class<?> subclass) {
        if (clazz.isInterface()) {
            return null;
        } else {
            Class<?> superclass = subclass.getSuperclass();

        }
        return null;
    }

    public Class<?>[] bounds(Class<?> subclass) {
        Type[] args = eval(subclass);
        Class<?>[] bounds = new Class<?>[args.length];
        for (int i = 0; i < args.length; i++) {
            Type arg = args[i];
            Class<?> bound = simpleUpperBound(arg);
            bounds[i] = bound;
        }
        return bounds;
    }

    static Class<?> simpleUpperBound(Type arg) {
        if (arg instanceof TypeVariable<?>) {
            TypeVariable<?> argVar = (TypeVariable<?>) arg;
            Type[] argBounds = argVar.getBounds();
            if (argBounds.length > 0) {
                return simpleUpperBound(argBounds[0]);
            }
        }
        return Object.class;
    }

    Type[] reorder(TypeVariable<?>[] declVars, Type[] superArgs, Type[] actualTypeArgs) {
        Type[] mapped = new Type[superArgs.length];
        V: for (int i = 0; i < superArgs.length; i++) {
            Type superArg = superArgs[i];
            if (superArg instanceof TypeVariable<?>) {

                // declare-var[j] => actual-var[i], then args[j]
                TypeVariable<?> superTV = (TypeVariable<?>) superArg;
                String superName = superTV.getTypeName();
                for (int j = 0; j < declVars.length; j++) {
                    if (declVars[j].getName().equals(superName)) {
                        try {
                            mapped[i] = actualTypeArgs[j];
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println(1);
                        }
                        continue V;
                    }
                }
                throw new UnexpectedException("No type parameter with name " + superName + " is declared.");
            }
            mapped[i] = superArg;
        }
        return mapped;
    }

}
