package net.bodz.bas.code.util;

import net.bodz.bas.t.tuple.QualifiedName;

public final class ThisType {

    public static final QualifiedName QNAME = QualifiedName.of(ThisType.class);

    public static boolean is(QualifiedName type) {
        return QNAME.equals(type);
    }

}
