package net.bodz.bas.potato;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.TreeSet;

import net.bodz.bas.potato.element.IType;
import net.bodz.bas.potato.element.LinkedType;
import net.bodz.bas.t.order.PriorityComparator;
import net.bodz.mda.xjdoc.conv.ClassDocs;
import net.bodz.mda.xjdoc.model.ClassDoc;

public class PotatoLoader {

    private Map<Class<?>, IType> loadedTypes = new HashMap<>();

    public synchronized IType load(Class<?> clazz) {
        IType type = loadedTypes.get(clazz);

        if (type == null) {
            ClassDoc classDoc = ClassDocs.loadFromResource(clazz);

            List<IType> _types = new ArrayList<>();
            for (ITypeProvider provider : typeProviders) {
                IType _type = provider.getType(clazz, null, -1, classDoc);
                if (_type != null)
                    _types.add(_type);
            }

            type = new LinkedType(clazz, _types, classDoc);
        }

        return type;
    }

    static TreeSet<ITypeProvider> typeProviders;
    static {
        typeProviders = new TreeSet<>(PriorityComparator.INSTANCE);
        for (ITypeProvider provider : ServiceLoader.load(ITypeProvider.class)) {
            typeProviders.add(provider);
        }
    }

    public static TreeSet<ITypeProvider> getTypeProviders() {
        return typeProviders;
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
