package net.bodz.bas.types.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Comparator;

import net.bodz.bas.lang.err.IllegalUsageError;
import net.bodz.bas.lang.script.MethodSignature;
import net.bodz.bas.lang.util.Reflects;
import net.bodz.bas.nls.TypesNLS;
import net.bodz.bas.types.Pair;

public class Comparators {

    /** compare Comparables */
    public static final STD     STD     = new STD();

    /** if not Comparable, then compare by toString() */
    public static final NATURAL NATURAL = new NATURAL();

    static class STD implements Comparator<Object> {
        @SuppressWarnings("unchecked")
        @Override
        public int compare(Object a, Object b) {
            if (a == b)
                return 0;
            if (a == null)
                return -1;
            if (b == null)
                return 1;
            if (a instanceof Comparable) {
                Comparable<Object> _a = ((Comparable<Object>) a);
                return _a.compareTo(b);
            }
            throw new IllegalUsageError(TypesNLS.getString("Comparators.0") + a.getClass() //$NON-NLS-1$
                    + TypesNLS.getString("Comparators.1") + b.getClass()); //$NON-NLS-1$
        }
    }

    static class NATURAL implements Comparator<Object> {
        @Override
        public int compare(Object a, Object b) {
            if (a == b)
                return 0;
            int t = STD.compare(a, b);
            if (t != 0)
                return t;
            String sa = a.toString();
            String sb = b.toString();
            return sa.compareTo(sb);
        }
    }

    public static final INT    INT    = new INT();
    public static final LONG   LONG   = new LONG();
    public static final FLOAT  FLOAT  = new FLOAT();
    public static final DOUBLE DOUBLE = new DOUBLE();

    static class INT implements Comparator<Integer> {
        @Override
        public int compare(Integer a, Integer b) {
            return a - b;
        }
    }

    static class LONG implements Comparator<Long> {
        @Override
        public int compare(Long a, Long b) {
            return a < b ? -1 : a > b ? 1 : 0;
        }
    }

    static class FLOAT implements Comparator<Float> {
        @Override
        public int compare(Float a, Float b) {
            return a < b ? -1 : a > b ? 1 : 0;
        }
    }

    static class DOUBLE implements Comparator<Double> {
        @Override
        public int compare(Double a, Double b) {
            return a < b ? -1 : a > b ? 1 : 0;
        }
    }

    /** compare type name lexically */
    public static final TYPE TYPE = new TYPE();

    static class TYPE implements Comparator<Object> {
        @Override
        public int compare(Object l, Object r) {
            if (l == r)
                return 0;
            if (l == null)
                return -1;
            if (r == null)
                return 1;
            Class<?> lt = (Class<?>) l;
            Class<?> rt = (Class<?>) r;
            return lt.getName().compareTo(rt.getName());
        }
    }

    /** compare by type hierarchically */
    public static final TYPE_HIER  TYPE_HIER  = new TYPE_HIER();

    /** compare by type-list hierarchically */
    public static final TYPES_HIER TYPES_HIER = new TYPES_HIER();

    // @Deprecated
    static Class<?> getSuper(Class<?> type) {
        if (type.isInterface()) {
            Class<?>[] intf = type.getInterfaces();
            // return min(intf)
            if (intf.length > 0)
                return intf[0];
        }
        return type.getSuperclass();
    }

    static class TYPE_HIER implements Comparator<Object> {
        @Override
        public int compare(Object l, Object r) {
            if (l == r)
                return 0;
            if (l == null)
                return -1;
            if (r == null)
                return 1;
            Class<?> lt = (Class<?>) l;
            Class<?> rt = (Class<?>) r;
            if (lt.isAssignableFrom(rt))
                return -1;
            if (rt.isAssignableFrom(lt))
                return 1;

            // lt = findFirstIncompat(lt, rt)
            Class<?> com = getSuper(lt);
            while (com != null) {
                if (com.isAssignableFrom(rt))
                    break;
                lt = com;
                com = getSuper(com);
            }
            // maybe com == null if lt.isInterface.

            // rt = firstJustUnderCom(com, rt)
            Class<?> rsup = getSuper(rt);
            while (rsup != null) {
                if (rsup == com)
                    break;
                rt = rsup;
                rsup = getSuper(rsup);
            }
            // maybe rsup == null if com==null or rt.isInterface

            // if (com==null) ...?
            return lt.getName().compareTo(rt.getName());
        }
    }

    static class TYPES_HIER implements Comparator<Object> {
        @Override
        public int compare(Object l, Object r) {
            if (l == r)
                return 0;
            if (l == null)
                return -1;
            if (r == null)
                return 1;
            Class<?>[] lv = (Class<?>[]) l;
            Class<?>[] rv = (Class<?>[]) r;
            if (lv.length != rv.length)
                return lv.length - rv.length;
            for (int i = 0; i < lv.length; i++) {
                Class<?> lt = lv[i];
                Class<?> rt = rv[i];
                int c = TYPE_HIER.compare(lt, rt);
                if (c != 0)
                    return c;
            }
            return 0;
        }
    }

    /**
     * compare by method name, then parameters count, then each parameter's
     * typename
     */
    public static final METHOD METHOD = new METHOD();

    static class METHOD implements Comparator<Object> {
        @Override
        public int compare(Object l, Object r) {
            if (l == r)
                return 0;
            if (l == null)
                return -1;
            if (r == null)
                return 1;

            boolean lsig = l instanceof MethodSignature;
            boolean rsig = r instanceof MethodSignature;
            String ln = lsig ? ((MethodSignature) l).getName() : ((Method) l)
                    .getName();
            String rn = rsig ? ((MethodSignature) r).getName() : ((Method) r)
                    .getName();
            int cmp = ln.compareTo(rn);
            if (cmp != 0)
                return cmp;

            Class<?>[] lt = lsig ? ((MethodSignature) l).getParameterTypes()
                    : ((Method) l).getParameterTypes();
            Class<?>[] rt = rsig ? ((MethodSignature) r).getParameterTypes()
                    : ((Method) r).getParameterTypes();
            cmp = lt.length - rt.length;
            if (cmp != 0)
                return cmp;

            for (int i = 0; i < lt.length; i++) {
                if (!lt[i].equals(rt[i])) {
                    cmp = lt[i].getName().compareTo(rt[i].getName());
                    if (cmp != 0)
                        return cmp;
                }
            }
            return 0;
        }
    }

    /** compare by string length, then string text */
    public static final STRLEN STRLEN = new STRLEN();

    static class STRLEN implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            if (a == b)
                return 0;
            if (a == null)
                return -1;
            if (b == null)
                return 1;
            int t = a.length() - b.length();
            if (t != 0)
                return t;
            return a.compareTo(b);
        }
    }

    /** compare by key of pair */
    public static final PAIRK PAIRK = new PAIRK();

    /** compare by value of pair */
    public static final PAIRV PAIRV = new PAIRV();

    static class PAIRK implements Comparator<Object> {
        @Override
        public int compare(Object a, Object b) {
            if (a == b)
                return 0;
            if (a == null)
                return -1;
            if (b == null)
                return 1;
            return STD.compare(((Pair<?, ?>) a).first, ((Pair<?, ?>) b).first);
        }
    }

    static class PAIRV implements Comparator<Object> {
        @Override
        public int compare(Object a, Object b) {
            if (a == b)
                return 0;
            if (a == null)
                return -1;
            if (b == null)
                return 1;
            return STD
                    .compare(((Pair<?, ?>) a).second, ((Pair<?, ?>) b).second);
        }
    }

    public static <T> Comparator<T> descend(final Comparator<T> comparator) {
        return new Comparator<T>() {
            public int compare(T a, T b) {
                return -comparator.compare(a, b);
            };
        };
    }

    public static Comparator<Object> array(final int[] columns) {
        return new Comparator<Object>() {
            @Override
            public int compare(Object av, Object bv) {
                if (av == bv)
                    return 0;
                if (av == null || bv == null)
                    return -1;
                if (bv == null)
                    return 1;
                Object[] al = (Object[]) av;
                Object[] bl = (Object[]) bv;
                for (int col : columns) {
                    boolean rev = col < 0;
                    if (rev)
                        col = -col;
                    Object a = col < al.length ? al[col] : null;
                    Object b = col < bl.length ? bl[col] : null;
                    int t = STD.compare(a, b);
                    if (t != 0)
                        return t;
                    // maybe non-comparable
                    continue;
                }
                return 0;
            }
        };
    }

    public static class MemberFieldComparator implements Comparator<Object> {

        private final Comparator<Object> comparator;
        protected final Field            memberField;

        public MemberFieldComparator(Comparator<Object> comparator,
                Field memberField) {
            assert memberField != null;
            assert comparator != null;
            this.comparator = comparator;
            this.memberField = memberField;
        }

        @Override
        public int compare(Object aobj, Object bobj) {
            if (aobj == bobj)
                return 0;
            if (aobj == null)
                return -1;
            if (bobj == null)
                return 1;
            Object a = Reflects.get(aobj, memberField);
            Object b = Reflects.get(bobj, memberField);
            return comparator.compare(a, b);
        }

    }

    public static class MemberMethodComparator implements Comparator<Object> {

        protected final Method           memberMethod;
        protected final Object[]         args;
        private final Comparator<Object> comparator;

        public MemberMethodComparator(Comparator<Object> comparator,
                Method memberMethod, Object... args) {
            assert comparator != null;
            assert memberMethod != null;
            this.comparator = comparator;
            this.memberMethod = memberMethod;
            this.args = args;
        }

        @Override
        public int compare(Object aobj, Object bobj) {
            if (aobj == bobj)
                return 0;
            if (aobj == null)
                return -1;
            if (bobj == null)
                return 1;
            Object a = Reflects.invoke(aobj, memberMethod, args);
            Object b = Reflects.invoke(bobj, memberMethod, args);
            return comparator.compare(a, b);
        }

    }

    public static class MemberPropertyComparator extends MemberMethodComparator {

        public MemberPropertyComparator(Comparator<Object> comparator,
                PropertyDescriptor memberProperty) {
            super(comparator, memberProperty.getReadMethod());
            if (memberMethod == null)
                throw new IllegalArgumentException(TypesNLS.getString("Comparators.2") //$NON-NLS-1$
                        + memberProperty.getName() + TypesNLS.getString("Comparators.3")); //$NON-NLS-1$
        }

    }

    public static Comparator<Object> member(Comparator<Object> comparator,
            Field memberField) {
        return new MemberFieldComparator(comparator, memberField);
    }

    public static Comparator<Object> member(Comparator<Object> comparator,
            Method memberMethod, Object... args) {
        return new MemberMethodComparator(comparator, memberMethod, args);
    }

    public static Comparator<Object> member(Comparator<Object> comparator,
            PropertyDescriptor memberProperty) {
        return new MemberPropertyComparator(comparator, memberProperty);
    }

    public static Comparator<Object> member(Field member) {
        return member(STD, member);
    }

    public static Comparator<Object> member(Method member) {
        return member(STD, member);
    }

    public static Comparator<Object> member(PropertyDescriptor member) {
        return member(STD, member);
    }

}
