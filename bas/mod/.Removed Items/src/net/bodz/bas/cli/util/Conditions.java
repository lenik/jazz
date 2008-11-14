package net.bodz.bas.cli.util;

import net.bodz.bas.lang.Caller;
import net.bodz.bas.types.TextMap;
import net.bodz.bas.types.TextMap.HashTextMap;

@Deprecated
public class Conditions {

    TextMap<String> typealiases;
    {
        typealiases = new HashTextMap<String>();
    }

    public void setAlias(String libspec, String fqcn) {
        // System.out.println("set-alias: %" + libspec + "=" + fqcn);
        typealiases.put(libspec, fqcn);
    }

    /**
     * there is no preq for libspec in file.jar format.
     */
    public boolean fortype(String typeName) {
        if (typeName.startsWith("%")) {
            String alias = typeName.substring(1);
            typeName = typealiases.get(alias);
            if (typeName == null)
                return false;
        }
        ClassLoader loader = Caller.getCallerClassLoader();
        try {
            Class.forName(typeName, false, loader);
            return true;
        } catch (Throwable e) {
            return false;
        }
    }

}
