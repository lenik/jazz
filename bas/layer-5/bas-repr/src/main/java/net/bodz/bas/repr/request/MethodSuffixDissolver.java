package net.bodz.bas.repr.request;

import net.bodz.bas.repr.rest.RESTfulRequest;
import net.bodz.bas.repr.util.MethodNameUtil;

public class MethodSuffixDissolver
        implements ISuffixDissolver {

    @Override
    public boolean dissolveSuffix(String name, RESTfulRequest request) {
        if (!MethodNameUtil.isDefined(name))
            return false;

        request.setMethod(name);
        return true;
    }

    public static final MethodSuffixDissolver INSTANCE = new MethodSuffixDissolver();

}
