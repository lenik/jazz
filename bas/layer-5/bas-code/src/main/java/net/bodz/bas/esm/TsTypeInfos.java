package net.bodz.bas.esm;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class TsTypeInfos {

    static List<ITsTypeInfoProvider> providers = new ArrayList<>();
    static {
        for (ITsTypeInfoProvider provider : ServiceLoader.load(ITsTypeInfoProvider.class))
            providers.add(provider);
    }

    public static EsmName forClassName(String className) {
        for (ITsTypeInfoProvider provider : providers) {
            EsmName name = provider.forClassName(className);
            if (name != null)
                return name;
        }
        return null;
    }

    public static EsmName forClass(Class<?> clazz) {
        for (ITsTypeInfoProvider provider : providers) {
            EsmName name = provider.forClass(clazz);
            if (name != null)
                return name;
        }
        return null;
    }

}
