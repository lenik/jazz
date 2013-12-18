package net.bodz.bas.meta.codegen;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used to scan service classes and generate the index file.
 * 
 * The annotation is inheritable, so only need to annotate on the base type.
 * 
 * The result index file is <code>META-INF/services/&lt;base-type&gt;</code> by default.
 * 
 * @see EveryIndexedTypeCollector
 */
@Documented
@IndexedType(publishDir = PublishDir.features, includeAbstract = true, includeAnnotation = true)
@ExcludedFromIndex
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface IndexedType {

    /**
     * For annotated types, they could be published in META-INF/features
     */
    String publishDir() default PublishDir.services;

    Class<? extends IEtcFilesInstaller> etcFiles() default IEtcFilesInstaller.class;

    boolean includeAbstract() default false;

    boolean includeNonPublic() default false;

    boolean includeAnnotation() default false;

    /**
     * No longer index this type.
     * 
     * The collector utility should try to remove all types belong to the obsoleted indexed type.
     */
    boolean obsoleted() default false;

}
