package net.bodz.bas.javadoc;

import java.lang.reflect.Member;

public interface MemberJavadoc
        extends Javadoc {

    /**
     * Get the Java Reflect {@link Member} object.
     * 
     * @return The Java Reflect {@link Member} object.
     */
    Member getMember();

}
