package net.bodz.bas.bean.openbeans;

import net.bodz.bas.bean.api.IntrospectionException;

public class ObIntrospectionException
        extends IntrospectionException {

    private static final long serialVersionUID = 1L;

    public ObIntrospectionException(String message) {
        super(message);
    }

    public static ObIntrospectionException convert(com.googlecode.openbeans.IntrospectionException e) {
        return new ObIntrospectionException(e.getMessage());
    }

}
