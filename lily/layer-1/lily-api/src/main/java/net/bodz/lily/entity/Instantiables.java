package net.bodz.lily.entity;

public class Instantiables {

    public static <T> T _instantiate(Class<T> clazz)
            throws InstantiationException, IllegalAccessException {
        T instance;
        instance = clazz.newInstance();
        if (instance instanceof IReinitializable) {
            IReinitializable instantiable = (IReinitializable) instance;
            instantiable.reinit();
        }
        return instance;
    }

    public static <T extends IReinitializable> T instantiate(Class<T> clazz)
            throws InstantiationException, IllegalAccessException {
        T instance;
        instance = clazz.newInstance();
        instance.reinit();
        return instance;
    }

}
