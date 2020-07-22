package net.bodz.bas.fmt.json;

public interface IJsonOptions {

    /**
     * don't start with a key.
     */
    boolean isMixedIn();

    /**
     * don't wrap inside another json object.
     */
    boolean isSelfContained();

    NullJsonOptions NULL = new NullJsonOptions();

}

class NullJsonOptions
        implements IJsonOptions {

    @Override
    public boolean isMixedIn() {
        return false;
    }

    @Override
    public boolean isSelfContained() {
        return false;
    }

}