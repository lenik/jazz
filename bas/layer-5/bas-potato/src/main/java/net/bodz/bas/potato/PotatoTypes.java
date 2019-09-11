package net.bodz.bas.potato;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.bodz.bas.c.type.IndexedTypes;
import net.bodz.bas.meta.codegen.ExcludedFromIndex;
import net.bodz.bas.meta.codegen.IndexedTypeLoader;
import net.bodz.bas.meta.decl.Volatile;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.potato.element.MergedType;
import net.bodz.bas.t.order.PriorityComparator;
import net.bodz.mda.xjdoc.Xjdocs;
import net.bodz.mda.xjdoc.model.ClassDoc;

@IndexedTypeLoader(ITypeProvider.class)
@ExcludedFromIndex
public class PotatoTypes
        extends AbstractTypeProvider {
    static final Logger logger = LoggerFactory.getLogger(PotatoTypes.class);

    private Map<Class<?>, IType> cache = new HashMap<Class<?>, IType>();

    public PotatoTypes(int infoset) {
        super(infoset);
    }

    public PotatoTypes(int withInfo, int withoutInfo) {
        super(withInfo, withoutInfo);
    }

    @Override
    public int getDefaultInfoset() {
        return I_Default;
    }

    @Override
    public synchronized IType loadType(Class<?> clazz, Object obj, int infoset) {
        boolean isVolatile = clazz.isAnnotationPresent(Volatile.class);
        IType type = cache.get(clazz);
        if (type == null || isVolatile) {
            type = load(clazz);
            cache.put(clazz, type);
        }
        return type;
    }

    IType load(Class<?> clazz) {
        List<IType> type1v = new ArrayList<IType>();
        for (ITypeProvider provider : typeProviders) {
            int infoset = provider.getDefaultInfoset();
            IType type1 = provider.loadType(clazz, null, infoset);
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
        typeProviders = new TreeSet<ITypeProvider>(PriorityComparator.INSTANCE);
        for (Class<? extends ITypeProvider> providerClass : IndexedTypes.list(ITypeProvider.class, false)) {
            try {
                Constructor<? extends ITypeProvider> ctor = providerClass.getConstructor(int.class);
                ITypeProvider provider = ctor.newInstance(-1);
                typeProviders.add(provider);
            } catch (ReflectiveOperationException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static PotatoTypes instance = new PotatoTypes(0, 0);

    public static ITypeProvider getInstance() {
        return instance;
    }

}
