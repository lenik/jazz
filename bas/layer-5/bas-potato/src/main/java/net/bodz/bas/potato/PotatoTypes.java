package net.bodz.bas.potato;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.bodz.bas.meta.codegen.IndexedTypeLoader;
import net.bodz.bas.meta.decl.Volatile;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.potato.element.MergedType;
import net.bodz.bas.t.order.PriorityComparator;
import net.bodz.mda.xjdoc.Xjdocs;
import net.bodz.mda.xjdoc.model.ClassDoc;

@IndexedTypeLoader(ITypeProvider.class)
public class PotatoTypes {

    static final Logger logger = LoggerFactory.getLogger(PotatoTypes.class);

    private Map<Class<?>, IType> cache = new HashMap<>();

    public synchronized IType forClass(Class<?> clazz) {
        boolean isVolatile = clazz.isAnnotationPresent(Volatile.class);
        IType type = cache.get(clazz);
        if (type == null || isVolatile) {
            type = load(clazz);
            cache.put(clazz, type);
        }
        return type;
    }

    IType load(Class<?> clazz) {
        List<IType> type1v = new ArrayList<>();
        for (ITypeProvider provider : typeProviders) {
            IType type1 = provider.loadType(clazz, null, -1);
            if (type1 != null)
                type1v.add(type1);
        }

        switch (type1v.size()) {
        case 0:
            // IType.NULL?
            return null;
        case 1:
            return type1v.get(0);
        default:
            ClassDoc classDoc = Xjdocs.getDefaultProvider().getOrCreateClassDoc(clazz);
            return new MergedType(clazz, type1v, classDoc);
        }
    }

    static TreeSet<ITypeProvider> typeProviders;
    static {
        typeProviders = new TreeSet<>(PriorityComparator.INSTANCE);
        for (ITypeProvider provider : ServiceLoader.load(ITypeProvider.class)) {
            typeProviders.add(provider);
        }
    }

    static PotatoTypes instance = new PotatoTypes();

    public static PotatoTypes getInstance() {
        return instance;
    }

}
