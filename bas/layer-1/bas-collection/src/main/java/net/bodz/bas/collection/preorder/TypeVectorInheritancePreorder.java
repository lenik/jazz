package net.bodz.bas.collection.preorder;

public class TypeVectorInheritancePreorder
        extends AbstractPreorder<Class<?>[]> {

    @Override
    public Class<?>[] getPreceding(Class<?>[] v) {
        for (int i = v.length - 1; i >= 0; i--) {
            assert v[i] != null;
            if (v[i].isInterface())
                continue;
            if (v[i] != Object.class) {
                v[i] = v[i].getSuperclass();
                return v;
            }
        }
        return null;
    }

    private static final int INIT = 0;
    private static final int TEST_LESS = 1;
    private static final int TEST_GREATER = 2;

    @Override
    public int precompare(Class<?>[] av, Class<?>[] bv) {
        if (av == bv)
            return EQUALS;
        if (av.length != bv.length)
            return UNKNOWN;

        int status = INIT;

        for (int i = 0; i < av.length; i++) {
            Class<?> a = av[i];
            Class<?> b = bv[i];
            switch (status) {
            case INIT:
                if (a.equals(b))
                    ; // status = INIT;
                else if (a.isAssignableFrom(b))
                    status = TEST_LESS;
                else if (b.isAssignableFrom(a))
                    status = TEST_GREATER;
                else
                    return UNKNOWN;
                break;
            case TEST_LESS:
                if (!a.isAssignableFrom(b))
                    return UNKNOWN;
                break;
            case TEST_GREATER:
                if (!b.isAssignableFrom(a))
                    return UNKNOWN;
                break;
            }
        }
        switch (status) {
        case TEST_LESS:
            return LESS_THAN;
        case TEST_GREATER:
            return GREATER_THAN;
        case INIT:
        default:
            return EQUALS;
        }
    }

    static final TypeVectorInheritancePreorder instance = new TypeVectorInheritancePreorder();

    public static TypeVectorInheritancePreorder getInstance() {
        return instance;
    }

}
