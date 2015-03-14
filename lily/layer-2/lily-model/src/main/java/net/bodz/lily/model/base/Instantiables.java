package net.bodz.lily.model.base;

public class Instantiables {

    public static <T> T _instantiate(Class<T> clazz)
            throws InstantiationException, IllegalAccessException {
        T instance;
        instance = clazz.newInstance();
        if (instance instanceof IInstantiable) {
            IInstantiable instantiable = (IInstantiable) instance;
            instantiable.instantiate();
        }
        return instance;
    }

    public static <T extends IInstantiable> T instantiate(Class<T> clazz)
            throws InstantiationException, IllegalAccessException {
        T instance;
        instance = clazz.newInstance();
        instance.instantiate();
        return instance;
    }

}
