package net.bodz.bas.potato;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.bodz.bas.potato.element.IType;
import net.bodz.bas.potato.element.LinkedType;
import net.bodz.bas.t.order.PriorityComparator;
import net.bodz.mda.xjdoc.ClassDocLoader;
import net.bodz.mda.xjdoc.model.ClassDoc;

public class PotatoLoader {

    static final Logger logger = LoggerFactory.getLogger(PotatoLoader.class);

    private Map<Class<?>, IType> loadedTypes = new HashMap<>();

    public synchronized IType load(Class<?> clazz) {
        IType type = loadedTypes.get(clazz);

        if (type == null) {
            ClassDoc classDoc = ClassDocLoader.load(clazz);
            if (classDoc == null) {
                // logger.warn("No class doc for " + clazz);
                classDoc = ClassDoc.n_a(clazz.getName());
            }

            List<IType> _types = new ArrayList<>();
            for (ITypeProvider provider : typeProviders) {
                IType _type = provider.loadType(clazz, null, -1, classDoc);
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

}
