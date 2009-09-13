package net.bodz.bas.text.locale;

public class MergeDict extends _NLSDict {

    private final NLSDict master;

    public MergeDict(String title, NLSDict master, NLSDict second) {
        super(title, second);
        if (master == null)
            throw new NullPointerException("master");
        if (second == null)
            throw new NullPointerException("second");
        this.master = master;
    }

    @Override
    protected boolean _contains(String key) {
        return master.contains(key);
    }

    @Override
    protected Object _get(String key) {
        return master.get(key);
    }

    @Override
    protected Object _get(String key, Object def) {
        return master.get(key, def);
    }

}
