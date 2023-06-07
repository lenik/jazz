package net.bodz.bas.c.type;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.io.BCharOut;
import net.bodz.bas.io.ITreeOut;

public class GenericActualClassInfo {

    private final Class<?> clazz;
    private final TypeVariable<?>[] vars;
    private final Map<TypeVariable<?>, Integer> varIndex;
    private final Type[] args;
    private final Class<?>[] bounds;

    GenericActualClassInfo gsuper;
    GenericActualClassInfo[] ginterfaces;

    GenericActualClassInfo(Class<?> clazz) {
        this(clazz, null);
    }

    private GenericActualClassInfo(Class<?> clazz, Type[] args) {
        this.clazz = clazz;
        this.vars = clazz.getTypeParameters();
        this.varIndex = new HashMap<>();
        for (int i = 0; i < vars.length; i++)
            varIndex.put(vars[i], i);
        if (args != null) {
            if (args.length != vars.length)
                throw new IllegalArgumentException(String.format(//
                        "unmatched type parameter count: type %s, declared %d, actual %d", //
                        clazz.getCanonicalName(), vars.length, args.length));
        }
        this.args = args;
        this.bounds = _getBounds();
    }

    public Class<?> getRawClass() {
        return clazz;
    }

    public void ensureValidArgs(Type[] args) {
        if (!isValidArgs(args)) {
            throw new IllegalArgumentException(//
                    "invalid args: " + formatTypes(args));
        }
    }

    public boolean isValidArgs(Type[] args) {
        if (args == null)
            return true; // Use as raw subst.
        return args.length == vars.length;
    }

    public final boolean isGeneric() {
        return vars.length != 0;
    }

    public final boolean isNotGeneric() {
        return vars.length == 0;
    }

    public Type[] getArgs() {
        return args;
    }

    public GenericActualClassInfo otherArgs(Type[] args) {
        return new GenericActualClassInfo(clazz, args);
    }

    public boolean isComplete() {
        if (vars.length == 0)
            return true;
        if (args == null)
            return false;
        for (int i = 0; i < args.length; i++) {
            Type arg = args[i];
            if (arg instanceof Class<?>)
                continue;
            if (!GenericTypes.isComplete(arg))
                return false;
        }
        return true;
    }

    public Class<?>[] getBounds() {
        return bounds;
    }

    Class<?>[] _getBounds() {
        if (args == null)
            return null;
        Class<?>[] bounds = new Class<?>[args.length];
        for (int i = 0; i < args.length; i++) {
            Type arg = args[i];
            Class<?> upperBound = getReducedBound(arg, null);
            bounds[i] = upperBound;
        }
        return bounds;
    }

    Class<?> getReducedBound(Type arg, Class<?> subclassHint) {
        if (arg instanceof Class<?>)
            return (Class<?>) arg;
        if (arg instanceof TypeVariable<?>) {
            TypeVariable<?> argVar = (TypeVariable<?>) arg;

            Type[] argBounds = argVar.getBounds();
            if (argBounds.length == 0)
                throw new UnexpectedException("empty arg bound array.");

            Type match = argBounds[0];
            if (subclassHint != null)
                for (int i = 0; i < argBounds.length; i++) {
                    Class<?> upperBound = getReducedBound(argBounds[i], null);
                    if (subclassHint.isAssignableFrom(upperBound)) {
                        match = upperBound;
                        break;
                    }
                }
            return getReducedBound(match, null);
        }
        if (arg instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) arg;
            Type rawType = pt.getRawType(); // mostly a Class<?>.
            return getReducedBound(rawType, subclassHint);
        }
        return Object.class;
    }

    public GenericActualClassInfo getUpward(Class<?> clazz) {
        if (clazz.isInterface())
            return getInterface(clazz);
        else
            return getSuper(clazz);
    }

    public GenericActualClassInfo getSuper(Class<?> ancestor) {
        if (ancestor == null)
            throw new NullPointerException("ancestor");
        if (!ancestor.isAssignableFrom(clazz))
            throw new IllegalArgumentException("Not an ancestor: " + ancestor.getCanonicalName());
        GenericActualClassInfo gancestor = this;
        while (ancestor != gancestor.clazz) {
            gancestor = gancestor.getSuper();
        }
        return gancestor;
    }

    public GenericActualClassInfo getSuper() {
        if (gsuper == null)
            gsuper = _getSuper();
        return gsuper;
    }

    GenericActualClassInfo _getSuper() {
        GenericActualClassInfo gsuper = GenericTypes.getActualSuperInfo(clazz);
        if (gsuper == null)
            return null;
        if (gsuper.args != null)
            if (this.args != null) {
                Type[] superArgs = gsuper.args;
                int n = superArgs.length;
                Type[] substs = new Type[n];
                for (int i = 0; i < n; i++) {
                    Type arg = superArgs[i];
                    Integer index = varIndex.get(arg);
                    if (index != null)
                        arg = args[index];
                    substs[i] = arg;
                }
                return gsuper.otherArgs(substs);
            }
        return gsuper;
    }

    public GenericActualClassInfo getInterface(Class<?> ancestorInterface) {
        if (ancestorInterface == null)
            throw new NullPointerException("ancestorInterface");
        if (!ancestorInterface.isInterface())
            throw new IllegalArgumentException("Not an interface: " + ancestorInterface.getCanonicalName());
        if (!ancestorInterface.isAssignableFrom(clazz))
            throw new IllegalArgumentException("Not an ancestor: " + ancestorInterface.getCanonicalName());
        GenericActualClassInfo gancestorInterface = this;
        while (ancestorInterface != gancestorInterface.clazz) {
            boolean found = false;
            for (GenericActualClassInfo gbase : gancestorInterface.getInterfaces())
                if (ancestorInterface.isAssignableFrom(gbase.clazz)) {
                    gancestorInterface = gbase;
                    found = true;
                    break;
                }
            if (!found)
                throw new UnexpectedException();
        }
        return gancestorInterface;
    }

    public GenericActualClassInfo[] getInterfaces() {
        if (ginterfaces == null)
            ginterfaces = _getInterfaces();
        return ginterfaces;
    }

    GenericActualClassInfo[] _getInterfaces() {
        GenericActualClassInfo[] ginterfaces = GenericTypes.getActualInterfacesInfo(clazz);
        for (int ifaceIndex = 0; ifaceIndex < ginterfaces.length; ifaceIndex++) {
            GenericActualClassInfo ginterface = ginterfaces[ifaceIndex];
            if (ginterface.args != null)
                if (this.args != null) {
                    Type[] ifaceArgs = ginterface.args;
                    int n = ifaceArgs.length;
                    Type[] substs = new Type[n];
                    for (int i = 0; i < n; i++) {
                        Type arg = ifaceArgs[i];
                        Integer index = varIndex.get(arg);
                        if (index != null)
                            arg = args[index];
                        substs[i] = arg;
                    }
                    ginterfaces[ifaceIndex] = ginterface.otherArgs(substs);
                } // both args != null
        }
        return ginterfaces;
    }

    public GenericActualClassInfo getSubclass(Class<?> subclass) {
        if (!clazz.isAssignableFrom(subclass))
            throw new IllegalArgumentException("Invalid subclass: " + subclass.getCanonicalName());

        GenericActualClassInfo gsubclass = GenericTypes.getActualInfo(subclass);
        if (clazz.isInterface()) {
            return gsubclass.getInterface(clazz);
        } else {
            return gsubclass.getSuper(clazz);
        }
    }

    public void accept(IGenericActualClassInfoVisitor visitor) {
        getSuper();
        getInterfaces();

        visitor.beginClass(this);

        if (vars.length > 0)
            if (visitor.beginVars()) {
                int n = vars.length;
                for (int i = 0; i < n; i++)
                    visitor.var(i, vars[i]);
                visitor.endVars();
            }

        if (args != null && args.length > 0)
            if (visitor.beginArgs()) {
                int n = args.length;
                for (int i = 0; i < n; i++)
                    visitor.arg(i, args[i]);
                visitor.endArgs();
            }

        if (gsuper != null)
            visitor.gsuper(gsuper);

        if (ginterfaces != null && ginterfaces.length > 0) {
            if (visitor.beginInterfaces()) {
                int n = ginterfaces.length;
                for (int i = 0; i < n; i++) {
                    {
                        GenericActualClassInfo ginterface = ginterfaces[i];
                        if (ginterface != null)
                            visitor.ginterface(i, ginterface);
                    }
                }
                visitor.endInterfaces();
            }
        }

        visitor.endClass();
    }

    public String dump() {
        BCharOut buf = new BCharOut();
        dump(buf.indented());
        return buf.toString();
    }

    public void dump(ITreeOut out) {
        accept(new GenericActualClassInfoDumper(out));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(clazz.getCanonicalName() + ": ");
        if (vars != null)
            for (int i = 0; i < vars.length; i++) {
                sb.append("\n    ");
                sb.append("var ");
                sb.append(i);
                sb.append(": ");
                sb.append(vars[i]);
            }
        if (args != null)
            for (int i = 0; i < args.length; i++) {
                sb.append("\n    ");
                sb.append("arg ");
                sb.append(i);
                sb.append(": ");
                sb.append(formatTypes(args[i]));
            }
        return sb.toString();
    }

    static String formatTypes(Type... args) {
        if (args == null)
            return "(none)";
        StringBuilder sb = new StringBuilder(args.length * 40);
        for (int i = 0; i < args.length; i++) {
            if (i != 0)
                sb.append(", ");
            Type arg = args[i];
            boolean isVar = arg instanceof TypeVariable<?>;
            if (isVar) {
                TypeVariable<?> var = (TypeVariable<?>) arg;
                sb.append("var " + var.getName());
            } else {
                sb.append(arg.getTypeName());
            }
        }
        return sb.toString();
    }

}
