package net.bodz.bas.types.util;

import java.lang.reflect.Method;
import java.util.Comparator;

import net.bodz.bas.lang.script.MethodSignature;
import net.bodz.bas.types.Pair;

public class Comparators {

    public static final Comparator<Object> STD;
    public static final Comparator<Object> NATURAL;
    public static final Comparator<Object> TYPE;
    public static final Comparator<Object> TYPE_HIER;
    public static final Comparator<Object> METHOD;
    public static final Comparator<String> STRLEN;
    public static final Comparator<Object> PAIR1;
    public static final Comparator<Object> PAIR2;

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

    static {

        STD = new Comparator<Object>() {
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
                return 0;
            }
        };

        NATURAL = new Comparator<Object>() {
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
        };

        TYPE = new Comparator<Object>() {
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
        };

        TYPE_HIER = new Comparator<Object>() {
            private Class<?> getSuper(Class<?> type) {
                if (type.isInterface()) {
                    Class<?>[] intf = type.getInterfaces();
                    // return min(intf)
                    if (intf.length > 0)
                        return intf[0];
                }
                return type.getSuperclass();
            }

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
                Class<?> com = getSuper(lt);
                while (com != null) {
                    if (com.isAssignableFrom(rt))
                        break;
                    lt = com;
                    com = getSuper(com);
                }
                // maybe com == null if lt.isInterface.
                Class<?> rsup = getSuper(rt);
                while (rsup != null) {
                    if (rsup == com)
                        break;
                    rt = rsup;
                    rsup = getSuper(rsup);
                }
                // maybe rsup == null if com==null or rt.isInterface
                // if (com==null)
                return lt.getName().compareTo(rt.getName());
            }
        };

        METHOD = new Comparator<Object>() {
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
                String ln = lsig ? ((MethodSignature) l).getName()
                        : ((Method) l).getName();
                String rn = rsig ? ((MethodSignature) r).getName()
                        : ((Method) r).getName();
                int cmp = ln.compareTo(rn);
                if (cmp != 0)
                    return cmp;

                Class<?>[] lt = lsig ? ((MethodSignature) l)
                        .getParameterTypes() : ((Method) l).getParameterTypes();
                Class<?>[] rt = rsig ? ((MethodSignature) r)
                        .getParameterTypes() : ((Method) r).getParameterTypes();
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
        };

        STRLEN = new Comparator<String>() {
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
        };

        PAIR1 = new Comparator<Object>() {
            @Override
            public int compare(Object a, Object b) {
                if (a == b)
                    return 0;
                if (a == null)
                    return -1;
                if (b == null)
                    return 1;
                return STD.compare(((Pair<?, ?>) a).first,
                        ((Pair<?, ?>) b).first);
            }
        };

        PAIR2 = new Comparator<Object>() {
            @Override
            public int compare(Object a, Object b) {
                if (a == b)
                    return 0;
                if (a == null)
                    return -1;
                if (b == null)
                    return 1;
                return STD.compare(((Pair<?, ?>) a).second,
                        ((Pair<?, ?>) b).second);
            }
        };

    }

}
