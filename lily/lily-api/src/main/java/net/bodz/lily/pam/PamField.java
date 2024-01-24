package net.bodz.lily.pam;

import net.bodz.bas.err.ParseException;

public interface PamField {

    Class<?> getFieldType();

    String getName();

    boolean isOptional();

    String format(Object value);

    Object parse(String text)
            throws ParseException;

    /**
     * check if the value is valid, existed, enabled, not-expired, etc.
     */
    void validate(Object value)
            throws PamValidateException;

}
