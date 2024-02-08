package net.bodz.bas.esm;

import net.bodz.bas.codegen.QualifiedName;
import net.bodz.bas.t.preorder.PreorderTreeMap;

public class QualifiedNameMap<T>
        extends PreorderTreeMap<QualifiedName, T> {

    private static final long serialVersionUID = 1L;

    public QualifiedNameMap() {
        super(QualifiedName.PREORDER);
    }

}
