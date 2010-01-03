package net.bodz.bas.collection.comparator;

/**
 * Compare two classes by their tree position.
 * 
 * This comparator only deals with <b>class</b>, <b>array</b>, <b>enum</b>, but no
 * <code>interface</code>.
 */
public class ClassComparator
        implements NonNullComparator<Class<?>> {

    @Override
    public int compare(Class<?> c1, Class<?> c2) {
        if (c1.isInterface())
            throw new IllegalArgumentException("Can't compare instance: " + c1);
        if (c2.isInterface())
            throw new IllegalArgumentException("Can't compare instance: " + c2);

        if (c1.isPrimitive())
            if (c2.isPrimitive())
                return ClassNameComparator.getInstance().compare(c1, c2);
            else
                return -1;
        else if (c2.isPrimitive())
            return 1;

        if (c1.equals(c2))
            return 0;
        if (c1.isAssignableFrom(c2))
            return -1;
        if (c2.isAssignableFrom(c1))
            return 1;

        // Find Least Upper Bound
        Class<?> lub1 = c1;
        Class<?> lub2 = c2;
        Class<?> commonSuperType = lub1.getSuperclass();
        while (commonSuperType != null) {
            if (commonSuperType.isAssignableFrom(lub2))
                break;
            lub1 = commonSuperType;
            commonSuperType = commonSuperType.getSuperclass();
        }
        assert commonSuperType != null;

        Class<?> t = c2;
        while (true) {
            if (t == commonSuperType)
                break;
            lub2 = t;
            t = t.getSuperclass();
        }

        return ClassNameComparator.instance.compare(lub1, lub2);
    }

    static final ClassComparator instance = new ClassComparator();

    public static ClassComparator getInstance() {
        return instance;
    }

}
