package net.bodz.bas.meta.bean;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Workaround ofr java beans on Androids.
 *
 * @see java.beans.Transient
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface Transient {

}
