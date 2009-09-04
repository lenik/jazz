package net.bodz.bas.text.locale;

import net.bodz.bas.nls.GeneralNLS;

public class SystemDict extends ResourceBundleDict {

    SystemDict() {
        super(null, "System Dict", GeneralNLS.bundle);
    }

    private static NLSDict instance = new SystemDict();

    public static NLSDict getInstance() {
        return instance;
    }

    public static void setSystemDict(NLSDict systemDict) {
        SystemDict.instance = systemDict;
    }

}
