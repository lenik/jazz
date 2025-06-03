package net.bodz.bas.make;

public interface IKey<K>
        extends IKeyType<K>,
                IParameterizedKey<Object, K> {

    K getKey();

    @Override
    default Class<Object> getParameterType() {
        return Object.class;
    }

    @Override
    default K getKey(Object parameter) {
        return getKey();
    }

}
