package net.bodz.bas.log;

import java.lang.reflect.Array;

public class Trees {

    /**
     * Root&lt;T>: Tree&lt;T> <br>
     * Tree&lt;T>: { T, ChildList&lt;T> } <br>
     * ChildList&lt;T>: T | Tree&lt;T>
     */
    @SuppressWarnings("unchecked")
    public static <S, T> Object convert(T parent, Object tree,
            TreeConvert<S, T> converter) {
        if (tree == null)
            return converter.convert(parent, null);
        if (!tree.getClass().isArray())
            return converter.convert(parent, (S) tree);
        int len = Array.getLength(tree);
        assert len >= 1;
        Object dtree = Array.newInstance(Object.class, len);
        S snode = (S) Array.get(tree, 0);
        T dnode = converter.convert(parent, snode);
        Array.set(dtree, 0, dnode);
        for (int i = 1; i < len; i++) {
            S s = (S) Array.get(tree, i);
            Object t = convert(dnode, s, converter);
            Array.set(dtree, i, t);
        }
        return dtree;
    }

    public static <S, T> Object convert(Object tree, TreeConvert<S, T> converter) {
        return convert(null, tree, converter);
    }
    //
    // @SuppressWarnings("unchecked")
    // public static <S, T> Object convert(Object tree, TreeConvert<S, T>
    // converter) {
    // if (tree == null)
    // throw new IllegalArgumentException(
    // "can't guess type from a null value");
    // if (!tree.getClass().isArray())
    // return convert(tree, converter, (Class<T>) tree.getClass());
    // Object stest = Array.get(tree, 0);
    // if (stest == null)
    // throw new IllegalArgumentException(
    // "can't guess type from a null value");
    // return convert(tree, converter, (Class<T>) stest.getClass());
    // }

}
