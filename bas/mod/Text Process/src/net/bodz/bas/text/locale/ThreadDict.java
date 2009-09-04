package net.bodz.bas.text.locale;

public class ThreadDict extends _NLSDict {

    private final ThreadLocal<NLSDict> localDicts;

    ThreadDict() {
        super("Thread Local NLSDict");
        localDicts = new ThreadLocal<NLSDict>();
    }

    @Override
    public boolean contains(String key) {
        NLSDict dict = localDicts.get();
        if (dict != null)
            if (dict.contains(key))
                return true;
        NLSDict systemDict = SystemDict.getInstance();
        if (systemDict != null)
            return systemDict.contains(key);
        return false;
    }

    @Override
    public Object get(String key, Object def) {
        NLSDict dict = localDicts.get();
        if (dict != null) {
            Object value = dict.get(key, def);
            if (value != null)
                return value;
        }
        NLSDict systemDict = SystemDict.getInstance();
        if (systemDict != null)
            return systemDict.get(key, def);
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
