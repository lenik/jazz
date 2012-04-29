package net.bodz.bas.repr.request;

import net.bodz.bas.repr.rest.RESTfulRequest;


public interface ISuffixDissolver {

    boolean dissolveSuffix(String name, RESTfulRequest model);

}
