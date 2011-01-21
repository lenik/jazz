package net.bodz.bas.javadoc;

import java.lang.reflect.Field;

public interface FieldJavadoc
        extends MemberJavadoc {

    @Override
    Field getMember();

    /**
     * Get the information of the default value.
     * 
     * @return Default value information text, <code>null</code> if not available.
     */
    String getSeeText();

}
