package net.bodz.bas.repr.request;

public abstract class AbstractRequestPreprocessor
        implements IRequestPreprocessor {

    @Override
    public int getPriority() {
        return 0;
    }

}
