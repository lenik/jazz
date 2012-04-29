package net.bodz.bas.repr.request;

import net.bodz.bas.repr.rest.RESTfulRequest;

public interface IRequestPreprocessor {

    int getPriority();

    void preprocess(RESTfulRequest request);

}
