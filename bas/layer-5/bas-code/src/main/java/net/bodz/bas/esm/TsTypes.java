package net.bodz.bas.esm;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class TsTypes {

    static List<ITsTypeProvider> providers = new ArrayList<>();
    static {
        for (ITsTypeProvider provider : ServiceLoader.load(ITsTypeProvider.class))
            providers.add(provider);
    }

    public static EsmName forClassName(String className) {
        for (ITsTypeProvider provider : providers) {
            EsmName name = provider.forClassName(className);
            if (name != null)
                return name;
        }
        return null;
    }

    public static EsmName forClass(Class<?> clazz) {
        for (ITsTypeProvider provider : providers) {
            EsmName name = provider.forClass(clazz);
            if (name != null)
                return name;
        }
        return null;
    }

}
