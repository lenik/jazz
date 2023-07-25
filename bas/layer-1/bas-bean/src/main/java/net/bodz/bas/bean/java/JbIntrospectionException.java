package net.bodz.bas.bean.java;

import net.bodz.bas.bean.api.IntrospectionException;

public class JbIntrospectionException
        extends IntrospectionException {

    private static final long serialVersionUID = 1L;

    public JbIntrospectionException(String message) {
        super(message);
    }

    public static JbIntrospectionException convert(java.beans.IntrospectionException e) {
        return new JbIntrospectionException(e.getMessage());
    }

}
