package net.bodz.bas.potato;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.TreeSet;

import net.bodz.bas.potato.element.IPotatoElementProvider;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.potato.element.MutableType;
import net.bodz.bas.t.order.PriorityComparator;

public class PotatoLoader {

    private Map<Class<?>, IType> loadedTypes = new HashMap<>();

    public synchronized IType load(Class<?> clazz) {
        IType type = loadedTypes.get(clazz);

        if (type == null) {
            MutableType newType = new MutableType(clazz);
            for (IPotatoElementProvider provider : potatoElementProviders) {
                provider.fillProperties(clazz, null, newType.getPropertyMap());
                provider.fillMethods(clazz, null, newType.getMethodMap());
                provider.fillConstructor(clazz, null, newType.getConstructorMap());
                provider.fillEventMap(clazz, null, newType.getEventMap());
            }
            type = newType;
        }

        return type;
    }

    static TreeSet<IPotatoElementProvider> potatoElementProviders;
    static {
        potatoElementProviders = new TreeSet<>(PriorityComparator.INSTANCE);
        for (IPotatoElementProvider provider : ServiceLoader.load(IPotatoElementProvider.class)) {
            potatoElementProviders.add(provider);
        }
    }

    static PotatoLoader instance = new PotatoLoader();

    public static PotatoLoader getInstance() {
        return instance;
    }

    public static IType getType(Class<?> clazz) {
        PotatoLoader potatoLoader = getInstance();
        IType type = potatoLoader.load(clazz);
        return type;
    }

}
