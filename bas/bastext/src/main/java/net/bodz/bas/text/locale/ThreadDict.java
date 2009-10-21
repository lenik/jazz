package net.bodz.bas.text.locale;

public class ThreadDict extends _NLSDict {

    private final ThreadLocal<NLSDict> localDicts;

    ThreadDict() {
        super("Thread Local NLSDict", SystemDict.getInstance());
        localDicts = new ThreadLocal<NLSDict>();
    }

    @Override
    public boolean _contains(String key) {
        NLSDict dict = localDicts.get();
        if (dict != null)
            return dict.contains(key);
        return false;
    }

    @Override
    public Object _get(String key, Object def) {
        NLSDict dict = localDicts.get();
        if (dict != null) {
            Object value = dict.get(key);
            if (value != null || dict.contains(key))
                return value;
        }
        return def;
    }

    private static final ThreadDict instance = new ThreadDict();

    public static ThreadDict getInstance() {
        return instance;
    }

    public static NLSDict getCurrentThreadDict() {
        return instance.localDicts.get();
    }

    public static void setCurrentThreadDict(NLSDict currentThreadDict) {
        instance.localDicts.set(currentThreadDict);
    }

}
