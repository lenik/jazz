package net.bodz.bas.i18n.dict;

import net.bodz.bas.i18n.nls.GeneralNLS;

public class SystemDict extends ResourceBundleDict {

    SystemDict() {
        super("System Dict", GeneralNLS.bundle, null);
    }

    private static NLSDict instance = new SystemDict();

    public static NLSDict getInstance() {
        return instance;
    }

    public static void setSystemDict(NLSDict systemDict) {
        SystemDict.instance = systemDict;
    }

}
