package net.bodz.lily.tool.wsdoc;

import net.bodz.bas.c.type.ClassComparator;
import net.bodz.bas.t.order.AbstractNonNullComparator;

public class EntityNameOrder
        extends AbstractNonNullComparator<EntityInfo> {

    ClassComparator classComparator = ClassComparator.getInstance();

    @Override
    public int compareNonNull(EntityInfo o1, EntityInfo o2) {
        return classComparator.compareNonNull(o1.declaredClass, o2.declaredClass);
    }

    public static final EntityNameOrder INSTANCE = new EntityNameOrder();

}