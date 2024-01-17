package net.bodz.lily.model.base;

import org.joda.time.DateTime;

import net.bodz.bas.c.type.ModifiedClassNameResolver;
import net.bodz.lily.criteria.CriteriaBuilder;
import net.bodz.lily.criteria.ICriteriaBuilder;

/**
 * @see net.bodz.lily.model.base.CoObject
 */
public class StructRowCriteriaBuilder<self_t extends StructRowCriteriaBuilder<self_t>>
        extends CriteriaBuilder<self_t> {

    public final DateField<DateTime> creation = date("creation", DateTime.class);
    public final DateField<DateTime> lastmod = date("lastmod", DateTime.class);

    public final IntegerField version = integer("version");

    static ModifiedClassNameResolver[] criteriaClassResolvers = {
            // TODO cache-enable?
            new ModifiedClassNameResolver(null, "CriteriaBuilder", false), //
            new ModifiedClassNameResolver("dao.", "CriteriaBuilder", false), //
            new ModifiedClassNameResolver("impl.", "CriteriaBuilder", false), //
            new ModifiedClassNameResolver(null, 1, "db.", "CriteriaBuilder", false), //
    };

    public static <M extends ICriteriaBuilder<M>> Class<M> findCriteriaBuilderClass(Class<?> objClass) {
        for (ModifiedClassNameResolver resolver : criteriaClassResolvers) {
            @SuppressWarnings("unchecked")
            Class<M> clazz = (Class<M>) resolver.apply(objClass);
            if (clazz != null)
                return clazz;
        }
        return null;
    }

}
